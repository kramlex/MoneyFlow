///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBFILESGetTagsArg;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `GetTagsArg` struct.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBFILESGetTagsArg : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// Path to the items.
@property (nonatomic, readonly) NSArray<NSString *> *paths;

#pragma mark - Constructors

///
/// Full constructor for the struct (exposes all instance variables).
///
/// @param paths Path to the items.
///
/// @return An initialized instance.
///
- (instancetype)initWithPaths:(NSArray<NSString *> *)paths;

- (instancetype)init NS_UNAVAILABLE;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `GetTagsArg` struct.
///
@interface DBFILESGetTagsArgSerializer : NSObject

///
/// Serializes `DBFILESGetTagsArg` instances.
///
/// @param instance An instance of the `DBFILESGetTagsArg` API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBFILESGetTagsArg` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBFILESGetTagsArg *)instance;

///
/// Deserializes `DBFILESGetTagsArg` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBFILESGetTagsArg` API object.
///
/// @return An instantiation of the `DBFILESGetTagsArg` object.
///
+ (DBFILESGetTagsArg *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END