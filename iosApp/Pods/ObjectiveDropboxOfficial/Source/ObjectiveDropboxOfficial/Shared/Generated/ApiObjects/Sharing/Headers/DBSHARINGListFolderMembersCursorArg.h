///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBSHARINGListFolderMembersCursorArg;
@class DBSHARINGMemberAction;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `ListFolderMembersCursorArg` struct.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBSHARINGListFolderMembersCursorArg : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// This is a list indicating whether each returned member will include a
/// boolean value `allow` in `DBSHARINGMemberPermission` that describes whether
/// the current user can perform the MemberAction on the member.
@property (nonatomic, readonly, nullable) NSArray<DBSHARINGMemberAction *> *actions;

/// The maximum number of results that include members, groups and invitees to
/// return per request.
@property (nonatomic, readonly) NSNumber *limit;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param actions This is a list indicating whether each returned member will
/// include a boolean value `allow` in `DBSHARINGMemberPermission` that
/// describes whether the current user can perform the MemberAction on the
/// member.
/// @param limit The maximum number of results that include members, groups and
/// invitees to return per request.
///
/// @return An initialized instance.
///
- (instancetype)initWithActions:(nullable NSArray<DBSHARINGMemberAction *> *)actions limit:(nullable NSNumber *)limit;

///
/// Convenience constructor (exposes only non-nullable instance variables with
/// no default value).
///
///
/// @return An initialized instance.
///
- (instancetype)initDefault;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `ListFolderMembersCursorArg` struct.
///
@interface DBSHARINGListFolderMembersCursorArgSerializer : NSObject

///
/// Serializes `DBSHARINGListFolderMembersCursorArg` instances.
///
/// @param instance An instance of the `DBSHARINGListFolderMembersCursorArg` API
/// object.
///
/// @return A json-compatible dictionary representation of the
/// `DBSHARINGListFolderMembersCursorArg` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBSHARINGListFolderMembersCursorArg *)instance;

///
/// Deserializes `DBSHARINGListFolderMembersCursorArg` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBSHARINGListFolderMembersCursorArg` API object.
///
/// @return An instantiation of the `DBSHARINGListFolderMembersCursorArg`
/// object.
///
+ (DBSHARINGListFolderMembersCursorArg *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END