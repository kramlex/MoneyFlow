///
/// Copyright (c) 2016 Dropbox, Inc. All rights reserved.
///
/// Auto-generated by Stone, do not modify.
///

#import <Foundation/Foundation.h>

#import "DBSerializableProtocol.h"

@class DBFILEPROPERTIESLookupError;

NS_ASSUME_NONNULL_BEGIN

#pragma mark - API Object

///
/// The `LookupError` union.
///
/// This class implements the `DBSerializable` protocol (serialize and
/// deserialize instance methods), which is required for all Obj-C SDK API route
/// objects.
///
@interface DBFILEPROPERTIESLookupError : NSObject <DBSerializable, NSCopying>

#pragma mark - Instance fields

/// The `DBFILEPROPERTIESLookupErrorTag` enum type represents the possible tag
/// states with which the `DBFILEPROPERTIESLookupError` union can exist.
typedef NS_CLOSED_ENUM(NSInteger, DBFILEPROPERTIESLookupErrorTag){
    /// (no description).
    DBFILEPROPERTIESLookupErrorMalformedPath,

    /// There is nothing at the given path.
    DBFILEPROPERTIESLookupErrorNotFound,

    /// We were expecting a file, but the given path refers to something that
    /// isn't a file.
    DBFILEPROPERTIESLookupErrorNotFile,

    /// We were expecting a folder, but the given path refers to something that
    /// isn't a folder.
    DBFILEPROPERTIESLookupErrorNotFolder,

    /// The file cannot be transferred because the content is restricted.  For
    /// example, sometimes there are legal restrictions due to copyright claims.
    DBFILEPROPERTIESLookupErrorRestrictedContent,

    /// (no description).
    DBFILEPROPERTIESLookupErrorOther,

};

/// Represents the union's current tag state.
@property (nonatomic, readonly) DBFILEPROPERTIESLookupErrorTag tag;

/// (no description). @note Ensure the `isMalformedPath` method returns true
/// before accessing, otherwise a runtime exception will be raised.
@property (nonatomic, readonly, copy) NSString *malformedPath;

#pragma mark - Constructors

///
/// Initializes union class with tag state of "malformed_path".
///
/// @param malformedPath (no description).
///
/// @return An initialized instance.
///
- (instancetype)initWithMalformedPath:(NSString *)malformedPath;

///
/// Initializes union class with tag state of "not_found".
///
/// Description of the "not_found" tag state: There is nothing at the given
/// path.
///
/// @return An initialized instance.
///
- (instancetype)initWithNotFound;

///
/// Initializes union class with tag state of "not_file".
///
/// Description of the "not_file" tag state: We were expecting a file, but the
/// given path refers to something that isn't a file.
///
/// @return An initialized instance.
///
- (instancetype)initWithNotFile;

///
/// Initializes union class with tag state of "not_folder".
///
/// Description of the "not_folder" tag state: We were expecting a folder, but
/// the given path refers to something that isn't a folder.
///
/// @return An initialized instance.
///
- (instancetype)initWithNotFolder;

///
/// Initializes union class with tag state of "restricted_content".
///
/// Description of the "restricted_content" tag state: The file cannot be
/// transferred because the content is restricted.  For example, sometimes there
/// are legal restrictions due to copyright claims.
///
/// @return An initialized instance.
///
- (instancetype)initWithRestrictedContent;

///
/// Initializes union class with tag state of "other".
///
/// @return An initialized instance.
///
- (instancetype)initWithOther;

- (instancetype)init NS_UNAVAILABLE;

#pragma mark - Tag state methods

///
/// Retrieves whether the union's current tag state has value "malformed_path".
///
/// @note Call this method and ensure it returns true before accessing the
/// `malformedPath` property, otherwise a runtime exception will be thrown.
///
/// @return Whether the union's current tag state has value "malformed_path".
///
- (BOOL)isMalformedPath;

///
/// Retrieves whether the union's current tag state has value "not_found".
///
/// @return Whether the union's current tag state has value "not_found".
///
- (BOOL)isNotFound;

///
/// Retrieves whether the union's current tag state has value "not_file".
///
/// @return Whether the union's current tag state has value "not_file".
///
- (BOOL)isNotFile;

///
/// Retrieves whether the union's current tag state has value "not_folder".
///
/// @return Whether the union's current tag state has value "not_folder".
///
- (BOOL)isNotFolder;

///
/// Retrieves whether the union's current tag state has value
/// "restricted_content".
///
/// @return Whether the union's current tag state has value
/// "restricted_content".
///
- (BOOL)isRestrictedContent;

///
/// Retrieves whether the union's current tag state has value "other".
///
/// @return Whether the union's current tag state has value "other".
///
- (BOOL)isOther;

///
/// Retrieves string value of union's current tag state.
///
/// @return A human-readable string representing the union's current tag state.
///
- (NSString *)tagName;

@end

#pragma mark - Serializer Object

///
/// The serialization class for the `DBFILEPROPERTIESLookupError` union.
///
@interface DBFILEPROPERTIESLookupErrorSerializer : NSObject

///
/// Serializes `DBFILEPROPERTIESLookupError` instances.
///
/// @param instance An instance of the `DBFILEPROPERTIESLookupError` API object.
///
/// @return A json-compatible dictionary representation of the
/// `DBFILEPROPERTIESLookupError` API object.
///
+ (nullable NSDictionary<NSString *, id> *)serialize:(DBFILEPROPERTIESLookupError *)instance;

///
/// Deserializes `DBFILEPROPERTIESLookupError` instances.
///
/// @param dict A json-compatible dictionary representation of the
/// `DBFILEPROPERTIESLookupError` API object.
///
/// @return An instantiation of the `DBFILEPROPERTIESLookupError` object.
///
+ (DBFILEPROPERTIESLookupError *)deserialize:(NSDictionary<NSString *, id> *)dict;

@end

NS_ASSUME_NONNULL_END