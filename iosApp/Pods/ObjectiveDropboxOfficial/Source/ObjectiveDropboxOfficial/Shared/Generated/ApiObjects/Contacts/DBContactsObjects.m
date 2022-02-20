///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

/// Arguments, results, and errors for the `Contacts` namespace.

#import "DBCONTACTSDeleteManualContactsArg.h"
#import "DBStoneSerializers.h"
#import "DBStoneValidators.h"

#pragma mark - API Object

@implementation DBCONTACTSDeleteManualContactsArg

#pragma mark - Constructors

- (instancetype)initWithEmailAddresses:(NSArray<NSString *> *)emailAddresses {
  [DBStoneValidators
   nonnullValidator:[DBStoneValidators
                        arrayValidator:nil
                              maxItems:nil
                         itemValidator:[DBStoneValidators
                                           nonnullValidator:[DBStoneValidators
                                                                stringValidator:nil
                                                                      maxLength:@(255)
                                                                        pattern:@"^['#&A-Za-z0-9._%+-]+@[A-Za-z0-9-][A-"
                                                                                @"Za-z0-9.-]*\\.[A-Za-z]{2,15}$"]]]](
      emailAddresses);

  self = [super init];
  if (self) {
    _emailAddresses = emailAddresses;
  }
  return self;
}

#pragma mark - Serialization methods

+ (nullable NSDictionary<NSString *, id> *)serialize:(id)instance {
  return [DBCONTACTSDeleteManualContactsArgSerializer serialize:instance];
}

+ (id)deserialize:(NSDictionary<NSString *, id> *)dict {
  return [DBCONTACTSDeleteManualContactsArgSerializer deserialize:dict];
}

#pragma mark - Debug Description method

- (NSString *)debugDescription {
  return [[DBCONTACTSDeleteManualContactsArgSerializer serialize:self] description];
}

#pragma mark - Copyable method

- (instancetype)copyWithZone:(NSZone *)zone {
#pragma unused(zone)
  /// object is immutable
  return self;
}

#pragma mark - Hash method

- (NSUInteger)hash {
  NSUInteger prime = 31;
  NSUInteger result = 1;

  result = prime * result + [self.emailAddresses hash];

  return prime * result;
}

#pragma mark - Equality method

- (BOOL)isEqual:(id)other {
  if (other == self) {
    return YES;
  }
  if (!other || ![other isKindOfClass:[self class]]) {
    return NO;
  }
  return [self isEqualToDeleteManualContactsArg:other];
}

- (BOOL)isEqualToDeleteManualContactsArg:(DBCONTACTSDeleteManualContactsArg *)aDeleteManualContactsArg {
  if (self == aDeleteManualContactsArg) {
    return YES;
  }
  if (![self.emailAddresses isEqual:aDeleteManualContactsArg.emailAddresses]) {
    return NO;
  }
  return YES;
}

@end

#pragma mark - Serializer Object

@implementation DBCONTACTSDeleteManualContactsArgSerializer

+ (NSDictionary<NSString *, id> *)serialize:(DBCONTACTSDeleteManualContactsArg *)valueObj {
  NSMutableDictionary *jsonDict = [[NSMutableDictionary alloc] init];

  jsonDict[@"email_addresses"] = [DBArraySerializer serialize:valueObj.emailAddresses
                                                    withBlock:^id(id elem0) {
                                                      return elem0;
                                                    }];

  return [jsonDict count] > 0 ? jsonDict : nil;
}

+ (DBCONTACTSDeleteManualContactsArg *)deserialize:(NSDictionary<NSString *, id> *)valueDict {
  NSArray<NSString *> *emailAddresses = [DBArraySerializer deserialize:valueDict[@"email_addresses"]
                                                             withBlock:^id(id elem0) {
                                                               return elem0;
                                                             }];

  return [[DBCONTACTSDeleteManualContactsArg alloc] initWithEmailAddresses:emailAddresses];
}

@end

#import "DBCONTACTSDeleteManualContactsError.h"
#import "DBStoneSerializers.h"
#import "DBStoneValidators.h"

#pragma mark - API Object

@implementation DBCONTACTSDeleteManualContactsError

@synthesize contactsNotFound = _contactsNotFound;

#pragma mark - Constructors

- (instancetype)initWithContactsNotFound:(NSArray<NSString *> *)contactsNotFound {
  self = [super init];
  if (self) {
    _tag = DBCONTACTSDeleteManualContactsErrorContactsNotFound;
    _contactsNotFound = contactsNotFound;
  }
  return self;
}

- (instancetype)initWithOther {
  self = [super init];
  if (self) {
    _tag = DBCONTACTSDeleteManualContactsErrorOther;
  }
  return self;
}

#pragma mark - Instance field accessors

- (NSArray<NSString *> *)contactsNotFound {
  if (![self isContactsNotFound]) {
    [NSException raise:@"IllegalStateException"
                format:@"Invalid tag: required DBCONTACTSDeleteManualContactsErrorContactsNotFound, but was %@.",
                       [self tagName]];
  }
  return _contactsNotFound;
}

#pragma mark - Tag state methods

- (BOOL)isContactsNotFound {
  return _tag == DBCONTACTSDeleteManualContactsErrorContactsNotFound;
}

- (BOOL)isOther {
  return _tag == DBCONTACTSDeleteManualContactsErrorOther;
}

- (NSString *)tagName {
  switch (_tag) {
  case DBCONTACTSDeleteManualContactsErrorContactsNotFound:
    return @"DBCONTACTSDeleteManualContactsErrorContactsNotFound";
  case DBCONTACTSDeleteManualContactsErrorOther:
    return @"DBCONTACTSDeleteManualContactsErrorOther";
  }

  @throw([NSException exceptionWithName:@"InvalidTag" reason:@"Tag has an unknown value." userInfo:nil]);
}

#pragma mark - Serialization methods

+ (nullable NSDictionary<NSString *, id> *)serialize:(id)instance {
  return [DBCONTACTSDeleteManualContactsErrorSerializer serialize:instance];
}

+ (id)deserialize:(NSDictionary<NSString *, id> *)dict {
  return [DBCONTACTSDeleteManualContactsErrorSerializer deserialize:dict];
}

#pragma mark - Debug Description method

- (NSString *)debugDescription {
  return [[DBCONTACTSDeleteManualContactsErrorSerializer serialize:self] description];
}

#pragma mark - Copyable method

- (instancetype)copyWithZone:(NSZone *)zone {
#pragma unused(zone)
  /// object is immutable
  return self;
}

#pragma mark - Hash method

- (NSUInteger)hash {
  NSUInteger prime = 31;
  NSUInteger result = 1;

  switch (_tag) {
  case DBCONTACTSDeleteManualContactsErrorContactsNotFound:
    result = prime * result + [self.contactsNotFound hash];
    break;
  case DBCONTACTSDeleteManualContactsErrorOther:
    result = prime * result + [[self tagName] hash];
    break;
  }

  return prime * result;
}

#pragma mark - Equality method

- (BOOL)isEqual:(id)other {
  if (other == self) {
    return YES;
  }
  if (!other || ![other isKindOfClass:[self class]]) {
    return NO;
  }
  return [self isEqualToDeleteManualContactsError:other];
}

- (BOOL)isEqualToDeleteManualContactsError:(DBCONTACTSDeleteManualContactsError *)aDeleteManualContactsError {
  if (self == aDeleteManualContactsError) {
    return YES;
  }
  if (self.tag != aDeleteManualContactsError.tag) {
    return NO;
  }
  switch (_tag) {
  case DBCONTACTSDeleteManualContactsErrorContactsNotFound:
    return [self.contactsNotFound isEqual:aDeleteManualContactsError.contactsNotFound];
  case DBCONTACTSDeleteManualContactsErrorOther:
    return [[self tagName] isEqual:[aDeleteManualContactsError tagName]];
  }
  return YES;
}

@end

#pragma mark - Serializer Object

@implementation DBCONTACTSDeleteManualContactsErrorSerializer

+ (NSDictionary<NSString *, id> *)serialize:(DBCONTACTSDeleteManualContactsError *)valueObj {
  NSMutableDictionary *jsonDict = [[NSMutableDictionary alloc] init];

  if ([valueObj isContactsNotFound]) {
    jsonDict[@"contacts_not_found"] = [DBArraySerializer serialize:valueObj.contactsNotFound
                                                         withBlock:^id(id elem0) {
                                                           return elem0;
                                                         }];
    jsonDict[@".tag"] = @"contacts_not_found";
  } else if ([valueObj isOther]) {
    jsonDict[@".tag"] = @"other";
  } else {
    jsonDict[@".tag"] = @"other";
  }

  return [jsonDict count] > 0 ? jsonDict : nil;
}

+ (DBCONTACTSDeleteManualContactsError *)deserialize:(NSDictionary<NSString *, id> *)valueDict {
  NSString *tag = valueDict[@".tag"];

  if ([tag isEqualToString:@"contacts_not_found"]) {
    NSArray<NSString *> *contactsNotFound = [DBArraySerializer deserialize:valueDict[@"contacts_not_found"]
                                                                 withBlock:^id(id elem0) {
                                                                   return elem0;
                                                                 }];
    return [[DBCONTACTSDeleteManualContactsError alloc] initWithContactsNotFound:contactsNotFound];
  } else if ([tag isEqualToString:@"other"]) {
    return [[DBCONTACTSDeleteManualContactsError alloc] initWithOther];
  } else {
    return [[DBCONTACTSDeleteManualContactsError alloc] initWithOther];
  }
}

@end