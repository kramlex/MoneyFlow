package com.prof18.moneyflow.di

import com.prof18.moneyflow.data.MoneyRepositoryImpl
import com.prof18.moneyflow.data.db.DatabaseSource
import com.prof18.moneyflow.data.db.DatabaseSourceImpl
import com.prof18.moneyflow.database.DatabaseHelper
import com.prof18.moneyflow.debugLog
import com.prof18.moneyflow.domain.repository.MoneyRepository
import com.prof18.moneyflow.presentation.AddTransactionUseCaseIos
import com.prof18.moneyflow.presentation.CategoriesUseCaseIos
import com.prof18.moneyflow.presentation.HomeUseCaseIos
import com.prof18.moneyflow.presentation.addtransaction.AddTransactionUseCase
import com.prof18.moneyflow.presentation.categories.CategoriesUseCase
import com.prof18.moneyflow.presentation.home.HomeUseCase
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import kotlinx.coroutines.Dispatchers
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun initKoinIos(): KoinApplication = initKoin()

actual val platformModule = module {
    single<Settings> { KeychainSettings(service = "MoneyFlow") }

    scope(named(MONEY_FLOW_SCOPE_NAME)) {
        scoped {
            DatabaseHelper.setupDatabase()
            DatabaseHelper.instance
        }

        scoped<DatabaseSource> { DatabaseSourceImpl(get(), Dispatchers.Main) }
        scoped<MoneyRepository> { MoneyRepositoryImpl(get()) }

        // Use Cases
        scoped { HomeUseCase(get(), get()) }
        scoped { HomeUseCaseIos(get()) }

        scoped { AddTransactionUseCase(get()) }
        scoped { AddTransactionUseCaseIos(get()) }

        scoped { CategoriesUseCase(get()) }
        scoped { CategoriesUseCaseIos(get()) }
    }
}

fun Koin.openKoinScope(): Scope {
    debugLog("KOIN", "Opening Koin Scope")
    return this.createScope(MONEY_FLOW_SCOPE_ID, named(MONEY_FLOW_SCOPE_NAME))
}

fun Koin.getScope(): Scope {
    debugLog("KOIN", "Getting Koin Scope")
    return this.getScope(MONEY_FLOW_SCOPE_ID)
}

fun Koin.closeScope() {
    debugLog("KOIN", "Closing Koin Scope")
    this.getScope().close()
}

const val MONEY_FLOW_SCOPE_NAME = "MoneyFlowScope_Name"
const val MONEY_FLOW_SCOPE_ID = "MoneyFlowScope_ID"

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier, null)
}


fun Koin.getFromScope(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    val scope = getScope(MONEY_FLOW_SCOPE_ID)
    return scope.get(kClazz)
}
