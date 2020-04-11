#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class DevtoolsDevTool, DevtoolsKotlinArray, DevtoolsPreferencesDevTool, DevtoolsTextTool, DevtoolsTime, DevtoolsTimeTool, DevtoolsToggleTool, DevtoolsDevToolsSources;

@protocol DevtoolsDevToolsStorage, DevtoolsDevTools, DevtoolsDevToolsParser, DevtoolsDevToolsSource, DevtoolsToolStore, DevtoolsDevToolsReader, DevtoolsPresenter, DevtoolsBaseView, DevtoolsDevToolPresenter, DevtoolsDevToolView, DevtoolsKotlinKClass, DevtoolsTextToolPresenter, DevtoolsTextToolView, DevtoolsTimeToolPresenter, DevtoolsTimeToolView, DevtoolsToggleToolPresenter, DevtoolsToggleToolView, DevtoolsConfigScreenPresenter, DevtoolsConfigScreenView, DevtoolsDevToolsListView, DevtoolsPreferencesToolStore, DevtoolsKotlinIterator, DevtoolsKotlinKDeclarationContainer, DevtoolsKotlinKAnnotatedElement, DevtoolsKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface DevtoolsBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface DevtoolsBase (DevtoolsBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface DevtoolsMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface DevtoolsMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorDevtoolsKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface DevtoolsNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface DevtoolsByte : DevtoolsNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface DevtoolsUByte : DevtoolsNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface DevtoolsShort : DevtoolsNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface DevtoolsUShort : DevtoolsNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface DevtoolsInt : DevtoolsNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface DevtoolsUInt : DevtoolsNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface DevtoolsLong : DevtoolsNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface DevtoolsULong : DevtoolsNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface DevtoolsFloat : DevtoolsNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface DevtoolsDouble : DevtoolsNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface DevtoolsBoolean : DevtoolsNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("DevToolsStorage")))
@protocol DevtoolsDevToolsStorage
@required
- (id _Nullable)getValueKey:(NSString *)key __attribute__((swift_name("getValue(key:)")));
- (BOOL)isEnabledKey:(NSString *)key __attribute__((swift_name("isEnabled(key:)")));
@property (readonly) NSDictionary<NSString *, DevtoolsDevTool *> *tools __attribute__((swift_name("tools")));
@end;

__attribute__((swift_name("DevTools")))
@protocol DevtoolsDevTools <DevtoolsDevToolsStorage>
@required
@property void (^onConfigUpdate)(void) __attribute__((swift_name("onConfigUpdate")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DevToolsCompanion")))
@interface DevtoolsDevToolsCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsDevTools>)createDevToolsSource:(DevtoolsKotlinArray *)devToolsSource onConfigUpdate:(void (^)(void))onConfigUpdate __attribute__((swift_name("create(devToolsSource:onConfigUpdate:)")));
@end;

__attribute__((swift_name("DevToolsParser")))
@protocol DevtoolsDevToolsParser
@required
- (NSDictionary<NSString *, DevtoolsDevTool *> *)getDevTools __attribute__((swift_name("getDevTools()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DevToolsParserCompanion")))
@interface DevtoolsDevToolsParserCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsDevToolsParser>)createDevToolsSources:(NSArray<id<DevtoolsDevToolsSource>> *)devToolsSources __attribute__((swift_name("create(devToolsSources:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DevToolsStorageImpl")))
@interface DevtoolsDevToolsStorageImpl : DevtoolsBase <DevtoolsDevToolsStorage>
- (instancetype)initWithTools:(NSDictionary<NSString *, DevtoolsDevTool *> *)tools __attribute__((swift_name("init(tools:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)getValueKey:(NSString *)key __attribute__((swift_name("getValue(key:)")));
- (BOOL)isEnabledKey:(NSString *)key __attribute__((swift_name("isEnabled(key:)")));
@property (readonly) NSDictionary<NSString *, DevtoolsDevTool *> *tools __attribute__((swift_name("tools")));
@end;

__attribute__((swift_name("DevTool")))
@interface DevtoolsDevTool : DevtoolsBase
- (instancetype)initWithTitle:(NSString * _Nullable)title description:(NSString *)description canBeDisabled:(BOOL)canBeDisabled defaultEnabledValue:(BOOL)defaultEnabledValue __attribute__((swift_name("init(title:description:canBeDisabled:defaultEnabledValue:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)getDefaultValue __attribute__((swift_name("getDefaultValue()")));
@property BOOL canBeDisabled __attribute__((swift_name("canBeDisabled")));
@property BOOL defaultEnabledValue __attribute__((swift_name("defaultEnabledValue")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@property (readonly) BOOL isEnabled __attribute__((swift_name("isEnabled")));
@property NSString *key __attribute__((swift_name("key")));
@property (readonly) id<DevtoolsToolStore> store __attribute__((swift_name("store")));
@property NSString * _Nullable title __attribute__((swift_name("title")));
@end;

__attribute__((swift_name("PreferencesDevTool")))
@interface DevtoolsPreferencesDevTool : DevtoolsDevTool
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithTitle:(NSString * _Nullable)title description:(NSString *)description canBeDisabled:(BOOL)canBeDisabled defaultEnabledValue:(BOOL)defaultEnabledValue __attribute__((swift_name("init(title:description:canBeDisabled:defaultEnabledValue:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (id)getDefaultValue __attribute__((swift_name("getDefaultValue()")));
@property (readonly) id<DevtoolsToolStore> store __attribute__((swift_name("store")));
@end;

__attribute__((swift_name("ToolStore")))
@protocol DevtoolsToolStore
@required
- (id _Nullable)restore __attribute__((swift_name("restore()")));
- (void)storeValue:(id _Nullable)value __attribute__((swift_name("store(value:)")));
@property BOOL isEnabled __attribute__((swift_name("isEnabled")));
@end;

__attribute__((swift_name("DevToolsReader")))
@protocol DevtoolsDevToolsReader
@required
- (NSDictionary<NSString *, DevtoolsDevTool *> *)getDevTools __attribute__((swift_name("getDevTools()")));
@end;

__attribute__((swift_name("DevToolsSource")))
@protocol DevtoolsDevToolsSource
@required
- (id<DevtoolsDevToolsReader>)getReader __attribute__((swift_name("getReader()")));
@end;

__attribute__((swift_name("Presenter")))
@protocol DevtoolsPresenter
@required
@end;

__attribute__((swift_name("BasePresenter")))
@interface DevtoolsBasePresenter : DevtoolsBase <DevtoolsPresenter>
- (instancetype)initWithView:(id<DevtoolsBaseView>)view __attribute__((swift_name("init(view:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<DevtoolsBaseView> view __attribute__((swift_name("view")));
@end;

__attribute__((swift_name("BaseView")))
@protocol DevtoolsBaseView
@required
@end;

__attribute__((swift_name("DevToolPresenter")))
@protocol DevtoolsDevToolPresenter <DevtoolsPresenter>
@required
- (void)onPersistToolState __attribute__((swift_name("onPersistToolState()")));
- (void)onToolBindTool:(DevtoolsDevTool *)tool __attribute__((swift_name("onToolBind(tool:)")));
- (void)onToolEnableToggleUpdatedEnabled:(BOOL)enabled __attribute__((swift_name("onToolEnableToggleUpdated(enabled:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DevToolPresenterCompanion")))
@interface DevtoolsDevToolPresenterCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsDevToolPresenter>)createView:(id<DevtoolsDevToolView>)view __attribute__((swift_name("create(view:)")));
@end;

__attribute__((swift_name("DevToolView")))
@protocol DevtoolsDevToolView <DevtoolsBaseView>
@required
- (void)hideEnableToggle __attribute__((swift_name("hideEnableToggle()")));
- (void)persistToolState __attribute__((swift_name("persistToolState()")));
- (void)setDevToolEnabledIsEnabled:(BOOL)isEnabled __attribute__((swift_name("setDevToolEnabled(isEnabled:)")));
- (void)setTitleTitle:(NSString * _Nullable)title __attribute__((swift_name("setTitle(title:)")));
- (void)showEnableToggle __attribute__((swift_name("showEnableToggle()")));
@property (readonly) BOOL isToolEnabled __attribute__((swift_name("isToolEnabled")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TextTool")))
@interface DevtoolsTextTool : DevtoolsPreferencesDevTool
- (instancetype)initWithDefaultValue:(id _Nullable)defaultValue hint:(NSString * _Nullable)hint __attribute__((swift_name("init(defaultValue:hint:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (id)getDefaultValue __attribute__((swift_name("getDefaultValue()")));
@property (readonly) id<DevtoolsKotlinKClass> configurationValueType __attribute__((swift_name("configurationValueType")));
@property (readonly) NSString * _Nullable hint __attribute__((swift_name("hint")));
@end;

__attribute__((swift_name("TextToolPresenter")))
@protocol DevtoolsTextToolPresenter <DevtoolsPresenter>
@required
- (void)onStoreConfigValueValue:(NSString *)value __attribute__((swift_name("onStoreConfigValue(value:)")));
- (void)onToolBindTool_:(DevtoolsTextTool *)tool __attribute__((swift_name("onToolBind(tool_:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TextToolPresenterCompanion")))
@interface DevtoolsTextToolPresenterCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsTextToolPresenter>)createView:(id<DevtoolsTextToolView>)view __attribute__((swift_name("create(view:)")));
@end;

__attribute__((swift_name("TextToolView")))
@protocol DevtoolsTextToolView <DevtoolsBaseView>
@required
- (void)setHintHint:(NSString * _Nullable)hint __attribute__((swift_name("setHint(hint:)")));
- (void)setInputDataTypeConfigurationValueType:(id<DevtoolsKotlinKClass>)configurationValueType __attribute__((swift_name("setInputDataType(configurationValueType:)")));
- (void)setTextValueValue:(NSString *)value __attribute__((swift_name("setTextValue(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Time")))
@interface DevtoolsTime : DevtoolsBase
- (instancetype)initWithDuration:(int64_t)duration __attribute__((swift_name("init(duration:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithDuration_:(NSString *)duration __attribute__((swift_name("init(duration_:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithDays:(int64_t)days hours:(int64_t)hours minutes:(int64_t)minutes seconds:(int64_t)seconds milliseconds:(int64_t)milliseconds __attribute__((swift_name("init(days:hours:minutes:seconds:milliseconds:)"))) __attribute__((objc_designated_initializer));
- (int64_t)inMilliseconds __attribute__((swift_name("inMilliseconds()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t days __attribute__((swift_name("days")));
@property (readonly) int64_t hours __attribute__((swift_name("hours")));
@property (readonly) int64_t milliseconds __attribute__((swift_name("milliseconds")));
@property (readonly) int64_t minutes __attribute__((swift_name("minutes")));
@property (readonly) int64_t seconds __attribute__((swift_name("seconds")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimeTool")))
@interface DevtoolsTimeTool : DevtoolsPreferencesDevTool
- (instancetype)initWithDays:(int64_t)days hours:(int64_t)hours minutes:(int64_t)minutes seconds:(int64_t)seconds milliseconds:(int64_t)milliseconds __attribute__((swift_name("init(days:hours:minutes:seconds:milliseconds:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (DevtoolsLong *)getDefaultValue __attribute__((swift_name("getDefaultValue()")));
@end;

__attribute__((swift_name("TimeToolPresenter")))
@protocol DevtoolsTimeToolPresenter <DevtoolsPresenter>
@required
- (void)onClickSelectedTime:(NSString *)selectedTime __attribute__((swift_name("onClick(selectedTime:)")));
- (void)onStoreConfigValueSelectedTime:(NSString *)selectedTime __attribute__((swift_name("onStoreConfigValue(selectedTime:)")));
- (void)onTimeSelectedTime:(DevtoolsTime *)time __attribute__((swift_name("onTimeSelected(time:)")));
- (void)onToolBindTool__:(DevtoolsTimeTool *)tool __attribute__((swift_name("onToolBind(tool__:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimeToolPresenterCompanion")))
@interface DevtoolsTimeToolPresenterCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsTimeToolPresenter>)createView:(id<DevtoolsTimeToolView>)view __attribute__((swift_name("create(view:)")));
@end;

__attribute__((swift_name("TimeToolView")))
@protocol DevtoolsTimeToolView <DevtoolsBaseView>
@required
- (void)displayTimeSelectionDialogTitle:(NSString * _Nullable)title time:(NSString *)time __attribute__((swift_name("displayTimeSelectionDialog(title:time:)")));
- (void)setTimeTime:(NSString *)time __attribute__((swift_name("setTime(time:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ToggleTool")))
@interface DevtoolsToggleTool : DevtoolsPreferencesDevTool
- (instancetype)initWithDefaultValue:(BOOL)defaultValue __attribute__((swift_name("init(defaultValue:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (DevtoolsBoolean *)getDefaultValue __attribute__((swift_name("getDefaultValue()")));
@end;

__attribute__((swift_name("ToggleToolPresenter")))
@protocol DevtoolsToggleToolPresenter <DevtoolsPresenter>
@required
- (void)onStoreConfigValueValue_:(BOOL)value __attribute__((swift_name("onStoreConfigValue(value_:)")));
- (void)onToolBindTool___:(DevtoolsToggleTool *)tool __attribute__((swift_name("onToolBind(tool___:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ToggleToolPresenterCompanion")))
@interface DevtoolsToggleToolPresenterCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsToggleToolPresenter>)createView:(id<DevtoolsToggleToolView>)view __attribute__((swift_name("create(view:)")));
@end;

__attribute__((swift_name("ToggleToolView")))
@protocol DevtoolsToggleToolView <DevtoolsBaseView>
@required
- (void)setValueValue:(BOOL)value __attribute__((swift_name("setValue(value:)")));
@end;

__attribute__((swift_name("DevToolsListView")))
@protocol DevtoolsDevToolsListView <DevtoolsBaseView>
@required
- (void)showDevToolsTools:(NSArray<DevtoolsDevTool *> *)tools __attribute__((swift_name("showDevTools(tools:)")));
@property (readonly) NSArray<id<DevtoolsDevToolView>> *devToolViews __attribute__((swift_name("devToolViews")));
@end;

__attribute__((swift_name("ConfigScreenPresenter")))
@protocol DevtoolsConfigScreenPresenter <DevtoolsPresenter>
@required
- (void)onApplyConfig __attribute__((swift_name("onApplyConfig()")));
- (void)onCreate __attribute__((swift_name("onCreate()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigScreenPresenterCompanion")))
@interface DevtoolsConfigScreenPresenterCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsConfigScreenPresenter>)createView:(id<DevtoolsConfigScreenView>)view devTools:(id<DevtoolsDevTools>)devTools devToolsList:(id<DevtoolsDevToolsListView>)devToolsList __attribute__((swift_name("create(view:devTools:devToolsList:)")));
@end;

__attribute__((swift_name("ConfigScreenView")))
@protocol DevtoolsConfigScreenView <DevtoolsBaseView>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DevToolsSources")))
@interface DevtoolsDevToolsSources : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)devToolsSources __attribute__((swift_name("init()")));
@end;

__attribute__((swift_name("PreferencesToolStore")))
@protocol DevtoolsPreferencesToolStore <DevtoolsToolStore>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PreferencesToolStoreCompanion")))
@interface DevtoolsPreferencesToolStoreCompanion : DevtoolsBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<DevtoolsPreferencesToolStore>)createTool:(DevtoolsDevTool *)tool __attribute__((swift_name("create(tool:)")));
@end;

__attribute__((swift_name("PreferencesToolStoreImpl")))
@interface DevtoolsPreferencesToolStoreImpl : DevtoolsBase <DevtoolsPreferencesToolStore>
- (instancetype)initWithTool:(DevtoolsDevTool *)tool __attribute__((swift_name("init(tool:)"))) __attribute__((objc_designated_initializer));
- (id)restore __attribute__((swift_name("restore()")));
- (void)storeValue:(id)value __attribute__((swift_name("store(value:)")));
@property BOOL isEnabled __attribute__((swift_name("isEnabled")));
@end;

@interface DevtoolsDevToolsSources (Extensions)
- (id<DevtoolsDevToolsSource>)memoryDevTools:(NSDictionary<NSString *, DevtoolsDevTool *> *)devTools __attribute__((swift_name("memory(devTools:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface DevtoolsKotlinArray : DevtoolsBase
+ (instancetype)arrayWithSize:(int32_t)size init:(id _Nullable (^)(DevtoolsInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (id _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<DevtoolsKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(id _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol DevtoolsKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol DevtoolsKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol DevtoolsKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol DevtoolsKotlinKClass <DevtoolsKotlinKDeclarationContainer, DevtoolsKotlinKAnnotatedElement, DevtoolsKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol DevtoolsKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
