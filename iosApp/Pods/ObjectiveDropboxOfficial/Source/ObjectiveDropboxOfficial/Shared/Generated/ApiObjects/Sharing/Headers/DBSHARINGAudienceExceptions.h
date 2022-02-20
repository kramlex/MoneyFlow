///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBSHARINGAudienceExceptionContentInfo;
@class DBSHARINGAudienceExceptions;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `AudienceExceptions` struct.
///
/// The total count and truncated list of information of content inside this
/// folder that has a different audience than the link on this folder. This is
/// only returned for folders.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBSHARINGAudienceExceptions : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// (no description).
@property (nonatomic, readonly) NSNumber *count;

/// A truncated list of some of the content that is an exception. The length of
/// this list could be smaller than the count since it is only a sample but will
/// not be empty as long as count is not 0.
@property (nonatomic, readonly) NSArray<DBSHARINGAudienceExceptionContentInfo *> *exceptions;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param count (no description).
/// @param exceptions A truncated list of some of the content that is an
/// exception. The length of this list could be smaller than the count since it
/// is only a sample but will not be empty as long as count is not 0.
///
/// @return An initialized instance.
///
- (instancetype)initWithCount:(NSNumber *)count
                   exceptions:(NSArray<DBSHARINGAudienceExceptionContentInfo *> *)exceptions;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `AudienceExceptions` struct.
///
@interface DBSHARINGAudienceExceptionsSerializer : NSObject

///
/// Serializes `DBSHARINGAudienceExceptions` instances.
///
/// @param instance An instance of the `DBSHARINGAudienceExceptions` API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBSHARINGAudienceExceptions` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBSHARINGAudienceExceptions *)instance;

///
/// Deserializes `DBSHARINGAudienceExceptions` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBSHARINGAudienceExceptions` API object.
///
/// @return An instantiation of the `DBSHARINGAudienceExceptions` object.
///
+ (DBSHARINGAudienceExceptions *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END