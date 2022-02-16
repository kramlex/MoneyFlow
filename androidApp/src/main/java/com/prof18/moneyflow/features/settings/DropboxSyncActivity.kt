package com.prof18.moneyflow.features.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.prof18.moneyflow.BuildConfig
import com.prof18.moneyflow.R
import com.prof18.moneyflow.dropboxapi.DropboxAuthorizationParam
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncAction
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncAction.ShowError
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncMetadataModel
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncMetadataModel.Error
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncMetadataModel.Loading
import com.prof18.moneyflow.presentation.dropboxsync.DropboxSyncMetadataModel.Success
import com.prof18.moneyflow.ui.style.AppMargins
import com.prof18.moneyflow.ui.style.MoneyFlowTheme
import com.prof18.moneyflow.utils.DropboxConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class DropboxSyncActivity : ComponentActivity() {

    private val viewModel: DropboxSyncViewModel by viewModel()
    private var isAuthOngoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyFlowTheme {

                val dropboxSyncModel by viewModel.dropboxSyncMetadataState.collectAsState()
                val isConnected by viewModel.isDropboxConnected.collectAsState()

                DropboxLoginContent(
                    dropboxSyncMetadataModel = dropboxSyncModel,
                    isConnected = isConnected,
                    connectDropbox = {
                        val authParam = DropboxAuthorizationParam(
                            activity = this@DropboxSyncActivity,
                            apiKey = BuildConfig.DROPBOX_APP_KEY,
                            clientIdentifier = DropboxConstants.DROPBOX_CLIENT_IDENTIFIER,
                            scopes = DropboxConstants.DROPBOX_SCOPES,
                        )
                        viewModel.startAuthFlow(authParam)
                        isAuthOngoing = true
                    },
                    backupOnDropbox = {
                        viewModel.backup()
                    },
                    restoreFromDropbox = {
                        viewModel.restore()
                    },
                    unlinkDropbox = {
                        viewModel.unlinkDropbox()
                    },
                    dropboxSyncAction = viewModel.dropboxSyncAction,
                    resetAction = { viewModel.resetAction() },
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isAuthOngoing) {
            viewModel.saveDropboxAuth()
            isAuthOngoing = false
        }
    }
}

@Composable
private fun DropboxLoginContent(
    dropboxSyncMetadataModel: DropboxSyncMetadataModel,
    isConnected: Boolean,
    connectDropbox: () -> Unit,
    backupOnDropbox: () -> Unit,
    restoreFromDropbox: () -> Unit,
    unlinkDropbox: () -> Unit,
    dropboxSyncAction: DropboxSyncAction?,
    resetAction: () -> Unit,
) {

    val scaffoldState = rememberScaffoldState()
    dropboxSyncAction?.let {
        when (it) {
            is ShowError -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    val uiErrorMessage = it.uiErrorMessage
                    val message = "${uiErrorMessage.message}\n${uiErrorMessage.nerdMessage}"
                    scaffoldState.snackbarHostState.showSnackbar(message)
                    resetAction()
                }
            }
            is DropboxSyncAction.Loading -> {
                // TODO()
            }
            is DropboxSyncAction.Success -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                    resetAction()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = AppMargins.regular)
                    .padding(top = AppMargins.regular)
            ) {
                Text(
                    text = stringResource(R.string.dropbox_sync),
                    style = MaterialTheme.typography.h4,
                )

                DropboxSyncMetadataUI(
                    metadataModel = dropboxSyncMetadataModel,
                    isDropboxConnected = isConnected,
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = AppMargins.regular)
            ) {

                if (isConnected) {

                    Text(
                        stringResource(R.string.dropbox_linked),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(AppMargins.regular)
                    )

                    Text(
                        stringResource(R.string.backup_to_dropbox),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { backupOnDropbox() }
                            .padding(AppMargins.regular)
                    )
                    Divider()
                    Text(
                        stringResource(R.string.restore_from_dropbox),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { restoreFromDropbox() }
                            .padding(AppMargins.regular)
                    )
                    Divider()
                    Text(
                        stringResource(R.string.unlink_dropbox),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { unlinkDropbox() }
                            .padding(AppMargins.regular)
                    )
                    Divider()

                } else {
                    Text(
                        stringResource(R.string.connect_dropbox),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { connectDropbox() }
                            .padding(AppMargins.regular)
                    )
                    Divider()
                }
            }
        }
    )
}

@Composable
private fun DropboxSyncMetadataUI(
    metadataModel: DropboxSyncMetadataModel,
    isDropboxConnected: Boolean,
) {
    if (isDropboxConnected) {
        when (metadataModel) {
            is Success -> {
                Column {
                    Text(
                        stringResource(id = R.string.dropbox_sync_time),
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = AppMargins.small)
                    )

                    Text(
                        metadataModel.latestUploadFormattedDate,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = AppMargins.small)
                    )

                    Text(
                        metadataModel.latestDownloadFormattedDate,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = AppMargins.small)
                    )

                    Text(
                        stringResource(id = R.string.dropbox_nerd_stats),
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = AppMargins.regular)
                    )


                    if (metadataModel.tlDrHashMessage != null) {
                        Text(
                            metadataModel.tlDrHashMessage!!,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = AppMargins.small)
                        )
                    }

                    Text(
                        metadataModel.latestUploadHash,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = AppMargins.small)
                    )

                    Text(
                        metadataModel.latestDownloadHash,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = AppMargins.small)
                    )
                }
            }
            is Error -> {
                Text(
                    metadataModel.errorMessage.message,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = AppMargins.small)
                )
            }
            Loading -> CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun DropboxLoginContentLightNotConnectedPreview() {
    MoneyFlowTheme {
        Surface {
            DropboxLoginContent(
                dropboxSyncMetadataModel = DropboxSyncMetadataModel.Success(
                    latestUploadFormattedDate = "Data not uploaded yet",
                    latestDownloadFormattedDate = "Data not downloaded yet",
                    latestUploadHash = "Data not uploaded yet",
                    latestDownloadHash = "Data not downloaded yet",
                    tlDrHashMessage = null,
                ),
                isConnected = false,
                connectDropbox = { },
                backupOnDropbox = { },
                restoreFromDropbox = {},
                unlinkDropbox = {},
                dropboxSyncAction = null,
                resetAction = {},
            )
        }
    }
}

@Preview
@Composable
fun DropboxLoginContentLightConnectedPreview() {
    MoneyFlowTheme {
        Surface {
            DropboxLoginContent(
                dropboxSyncMetadataModel = DropboxSyncMetadataModel.Success(
                    latestUploadFormattedDate = "12 July 2021 - 14:21:45",
                    latestDownloadFormattedDate = "12 July 2021 - 14:21:45",
                    latestUploadHash = "Last upload content hash",
                    latestDownloadHash = "Last download content hash",
                    tlDrHashMessage = "Contents are the same",
                ),
                isConnected = true,
                connectDropbox = { },
                backupOnDropbox = { },
                restoreFromDropbox = {},
                unlinkDropbox = {},
                dropboxSyncAction = null,
                resetAction = {},
            )
        }
    }
}

@Preview
@Composable
fun DropboxLoginContentDarkNotConnectedPreview() {
    MoneyFlowTheme(darkTheme = true) {
        Surface {
            DropboxLoginContent(
                dropboxSyncMetadataModel = DropboxSyncMetadataModel.Success(
                    latestUploadFormattedDate = "Data not uploaded yet",
                    latestDownloadFormattedDate = "Data not downloaded yet",
                    latestUploadHash = "Last upload content hash",
                    latestDownloadHash = "Last download content hash",
                    tlDrHashMessage = "Contents are the same",
                ),
                isConnected = false,
                connectDropbox = { },
                backupOnDropbox = { },
                restoreFromDropbox = {},
                unlinkDropbox = {},
                dropboxSyncAction = null,
                resetAction = {},
            )
        }
    }
}

@Preview
@Composable
fun DropboxLoginContentDarkConnectedPreview() {
    MoneyFlowTheme(darkTheme = true) {
        Surface {
            DropboxLoginContent(
                dropboxSyncMetadataModel = DropboxSyncMetadataModel.Success(
                    latestUploadFormattedDate = "12 July 2021 - 14:21:45",
                    latestDownloadFormattedDate = "12 July 2021 - 14:21:45",
                    latestUploadHash = "Last upload content hash",
                    latestDownloadHash = "Last download content hash",
                    tlDrHashMessage = "Contents are the same",
                ),
                isConnected = true,
                connectDropbox = { },
                backupOnDropbox = { },
                restoreFromDropbox = {},
                unlinkDropbox = {},
                dropboxSyncAction = null,
                resetAction = {},
            )
        }
    }
}