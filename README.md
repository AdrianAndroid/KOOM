[![license](https://img.shields.io/badge/license-Apache--2.0-brightgreen.svg)](https://github.com/KwaiAppTeam/KOOM/blob/master/LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://github.com/KwaiAppTeam/KOOM/wiki/home)
# KOOM
An OOM killer on mobile platform by Kwai. 

中文版本请参看[这里](README.zh-CN.md)

## Introduction

KOOM creates a mobile high performance online memory monitoring solution，which supplies a detailed report when OOM related problems are detected, and has solved a large number of OOM issues in the Kwai application. It's currently available on **Android**.

With the increasing complexity of mobile terminal business logic and the gradual popularity of scenarios with high memory requirements such as 4K codec and AR magic watch, the OOM problem has become the number one problem in the stability management of the Kuaishou client. 
In the daily version iteration process, OOM surges occasionally occur, and the online environment is very complicated. There are thousands of AB experiments. Pre-prevention and post-recovery cannot be achieved. Therefore, high-performance online memory monitoring solutions are urgently needed.

So how should OOM governance be built? At present, KOOM has the capability of monitoring leakage of Java Heap/Native Heap/Thread, and will build multi-dimensional and multi-business scenarios monitoring in the future.

## Features

### Java Leak Monitor
- The `koom-java-leak` module is used for Java Heap leak monitoring: it uses the Copy-on-write 
mechanism to fork the child process dump Java Heap, which solves the problem.
The app freezes for a long time during the dump. For details, please refer to [here](./koom-java-leak/README.md)
### Native Leak Monitor
- The `koom-native-leak` module is a Native Heap leak monitoring solution: use the [Tracing garbage collection](https://en.wikipedia.org/wiki/Tracing_garbage_collection) mechanism to analyze the entire Native Heap, and directly output the leaked memory information like: size/Allocating stacks/etc.; 
  greatly reduces the cost of analyzing and solving memory leaks for business students. For details, please refer to [here](./koom-native-leak/README.md)
### Thread Leak Monitor
- The `koom-thread-leak` module is used for Thread leak monitoring: it hooks the life cycle 
  function of the thread, and periodically reports the leaked thread information. For details, please refer to [here](./koom-thread-leak/README.md)

## License
KOOM is under the Apache license 2.0. For details check out the [LICENSE](./LICENSE).

## Change Log
Check out the [CHANGELOG.md](./CHANGELOG.md) for details of change history.

## Contributing
If you are interested in contributing, check out the [CONTRIBUTING.md](./CONTRIBUTING.md)

## Feedback
Welcome report [issues](https://github.com/KwaiAppTeam/KOOM/issues) or contact us in WeChat group.

<img src=./doc/images/wechat.jpg/>


positionSize=4,

hprofStringCache=
LongObjectScatterMap(values=[mStayAwake, null, ADD_STARTING_NOT_NEEDED, null, ENCODER, null, androidx.appcompat.view.menu.MenuBuilder$Callback, null, null, id_aa_contentHint, null, EXTRA_REBUILD_CONTEXT_APPLICATION_INFO, mTitleReady, null, capturePreambleMs, null, mTempColors, null, DATE_HEADER, null, MEASURED_HEIGHT_STATE_SHIFT, ActionBar_LayoutParams, null, null, RADIAL_GRADIENT, null, START_INTENT_NOT_RESOLVED, null, null, null, null, DAY_NARROW, java.nio.ByteBufferAsShortBuffer, serialPersistentFields, null, null, null, null, null, null, null, android.app.SystemServiceRegistry$40, null, null, ENOTDIR, QUALITY_HIGH_SPEED_HIGH, availableTimeZoneIds, DEG_TO_RAD, android.app.FragmentManagerState, null, FLASH_MODE_OFF, mResolveHoverRunnable, cardBackgroundColor, null, AnimatedStateListDrawableCompat_android_enterFadeDuration, GL_UNIFORM_NAME_LENGTH, analysisMaxTimesPerVersion, mLeakReasonTable, abc_ic_star_black_36dp, null, androidx.lifecycle.GenericLifecycleObserver, null, null, null, DISALLOW_ADJUST_VOLUME, NavigationView_elevation, TRANSACTION_getNetworkStatsTethering, null, TIMEUNIT_FACTORY, java.lang.Class, TYPE_ASSIST_READING_CONTEXT, mProcessGenres, KEYSTORE_PUBLIC_KEY_CLASS_NAME, RelativeLayout_Layout_layout_alignStart, null, TableRow_Cell, PAUSED_WAITING_TO_RETRY, GET_META_DATA, android.icu.text.DateTimePatternGenerator$FormatParser, TRANSACTION_setP2pModes, GL_DRAW_BUFFER15, Theme_textEditNoPasteWindowLayout, SHORT_GENERIC, sLegacyRequests, FIREWALL_CHAIN_NAME_DOZABLE, al, LATIN_FRAKTUR, permission, modeId, null, _SC_SEM_VALUE_MAX, RESET_PASSWORD_REQUIRE_ENTRY, MOUNT_UMS_PROMPT, mBaseCache, null, android.icu.text.UForwardCharacterIterator, UNIX_LINES, TAG_MERGE, EKU_anyExtendedKeyUsage, View_accessibilityTraversalBefore, EXTRA_RESTRICTIONS_LIST, VALUE_TYPE_INT, TYPE_WIFI, AndroidManifestPathPermission, com.android.internal.textservice.ITextServicesManager$Stub, GL_LINEAR_MIPMAP_NEAREST, DrawableStates_state_activated, KEYCODE_Y, TONE_DTMF_B, MODE_CREATE, android.content.IIntentSender$Stub$Proxy, GL_OPERAND2_ALPHA, CHUNK_VULW, FEATURE_REPORT_NAMESPACE_ATTRIBUTES, java.util.Hashtable$HashIterator, InsetDrawable_inset, SCHEDULE_SEND_RESULT_TRANSACTION, mIgnoreCheekPress, TRANSACTION_setUserRestriction, CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_RECORDING, fullBackground, SCREENLAYOUT_SIZE_LARGE, META_ALT_RELEASED, PROGRESS_VISIBILITY_OFF, STATUS_TIMESTAMP, TRANSACTION_opComplete, GET_TAG_FOR_INTENT_SENDER_TRANSACTION, LIGHT_NO_MOON, mActiveSensors, mDrawableError, mInsidePadding, mScrollView, SIP_ALWAYS, mVisible, mWasLongPress, metricsCache, org.apache.harmony.security.asn1.ASN1Type[], PI, pathToNames, CB_OPT_OUT_DIALOG, abc_dialog_title_material, Theme_MaterialComponents_Light_Dialog_MinWidth_Bridge, BLOCKS, mBackgroundResource, MTRANS_Y, ACTION_DIAL, scanPublicSourceDir, val$child, defaultStyle, STAND_ALONE_DAY_OF_WEEK_FIELD, null, null, aborted, mOnActionExpandListener, null, null, null, null, null, com.android.org.bouncycastle.jcajce.provider.digest.MD5, mShowActionModePopup, null, null, null, VIEWS_TAG, SO_ERROR, null, null, GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS_EXT, JET_PAUSE_UPDATE, instanceFields, null, null, null, null, null, DIRECTORY_DOCUMENTS, null, null, android.content.pm.ServiceInfo$1, mtrl_bottomappbar_fab_cradle_margin, null, null, null, com.google.android.material.shape.MaterialShapeUtils, Base_TextAppearance_AppCompat_Widget_ActionBar_Subtitle, null, null, GL_MAX_RENDERBUFFER_SIZE, itemFillColor, null, null, null, null, null, null, null, null, null, null, $keySelector, ENFORCE_HW_PERMISSION_MESSAGE, null, null, textAppearanceLargePopupMenu, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, debugInfos, TRANSACTION_getPreferredNetworkType, AndroidM





classNames=LongLongScatterMap(assigned=4940, mask=8191, hasEmptyKey=false, loadFactor=0.75),

classIndex=SortedBytesMap(longIdentifiers=false, bytesPerValue=17, bytesPerKey=4, bytesPerEntry=21, size=4940),

instanceIndex=SortedBytesMap(longIdentifiers=false, bytesPerValue=10, bytesPerKey=4, bytesPerEntry=14, size=90258),

objectArrayIndex=SortedBytesMap(longIdentifiers=false, bytesPerValue=11, bytesPerKey=4, bytesPerEntry=15, size=6431),

primitiveArrayIndex=SortedBytesMap(longIdentifiers=false, bytesPerValue=8, bytesPerKey=4, bytesPerEntry=12, size=67943),

gcRoots=[kshark.GcRoot$ThreadObject@24a36ee, kshark.GcRoot$JniLocal@6a178f, kshark.GcRoot$JniLocal@7f3ca1c, kshark.GcRoot$JniLocal@809c625, kshark.GcRoot$JniLocal@c26d3fa, kshark.GcRoot$JniLocal@e7a9dab, kshark.GcRoot$JniLocal@3816c08, kshark.GcRoot$JniLocal@3359fa1, kshark.GcRoot$NativeStack@bfe35c6, kshark.GcRoot$NativeStack@cc36987, kshark.GcRoot$NativeStack@ec540b4, kshark.GcRoot$ThreadObject@2eb94dd, kshark.GcRoot$ThreadObject@597e852, kshark.GcRoot$ThreadObject@be31723, kshark.GcRoot$JniLocal@8cfb420, kshark.GcRoot$NativeStack@a11a1d9, kshark.GcRoot$ThreadObject@fa8379e, kshark.GcRoot$JniLocal@dc6027f, kshark.GcRoot$NativeStack@f4bf24c, kshark.GcRoot$ThreadObject@9f18295, kshark.GcRoot$JniLocal@5dc2faa, kshark.GcRoot$NativeStack@cc2479b, kshark.GcRoot$NativeStack@d6be738, kshark.GcRoot$ThreadObject@4b4b311, kshark.GcRoot$JniLocal@fa59c76, kshark.GcRoot$NativeStack@873c277, kshark.GcRoot$ThreadObject@d943ee4, kshark.GcRoot$ThreadObject@ba06f4d, kshark.GcRoot$ThreadObject@7c70a02, kshark.GcRoot$ThreadObject@4580f13, kshark.GcRoot$ThreadObject@9c86550, kshark.GcRoot$ThreadObject@511b349, kshark.GcRoot$JniLocal@9fc44e, kshark.GcRoot$NativeStack@42a896f, kshark.GcRoot$ThreadObject@6d6867c, kshark.GcRoot$JniLocal@6393b05, kshark.GcRoot$NativeStack@137d75a, kshark.GcRoot$NativeStack@2004d8b, kshark.GcRoot$ThreadObject@6438e68, kshark.GcRoot$JniLocal@c978281, kshark.GcRoot$NativeStack@70c0f26, kshark.GcRoot$NativeStack@a243767, kshark.GcRoot$ThreadObject@af72914, kshark.GcRoot$JniLocal@338c5bd, kshark.GcRoot$NativeStack@899f7b2, kshark.GcRoot$NativeStack@8b2e303, kshark.GcRoot$ThreadObject@ca7c280, kshark.GcRoot$JniLocal@fb100b9, kshark.GcRoot$NativeStack@4abdcfe, kshark.GcRoot$NativeStack@4f6ac5f, kshark.GcRoot$ThreadObject@60686ac, kshark.GcRoot$JniLocal@2d7ef75, kshark.GcRoot$NativeStack@a64cb0a, kshark.GcRoot$NativeStack@683af7b, kshark.GcRoot$ThreadObject@9ab6198, kshark.GcRoot$JniLocal@9450df1, kshark.GcRoot$NativeStack@60c8dd6, kshark.GcRoot$NativeStack@13c857, kshark.GcRoot$ThreadObject@9c0ff44, kshark.GcRoot$JniLocal@78b982d, kshark.GcRoot$NativeStack@e9bb162, kshark.GcRoot$NativeStack@c2292f3, kshark.GcRoot$ThreadObject@e70cbb0, kshark.GcRoot$JniLocal@e368a29, kshark.GcRoot$NativeStack@781ae, kshark.GcRoot$NativeStack@7496b4f, kshark.GcRoot$ThreadObject@70ef2dc, kshark.GcRoot$JniLocal@1849fe5, kshark.GcRoot$NativeStack@e4e0aba, kshark.GcRoot$NativeStack@45b6d6b, kshark.GcRoot$ThreadObject@20660c8, kshark.GcRoot$JniLocal@e45561, kshark.GcRoot$NativeStack@d421886, kshark.GcRoot$NativeStack@6417547, kshark.GcRoot$ThreadObject@784c174, kshark.GcRoot$JniLocal@a2fe69d, kshark.GcRoot$NativeStack@9173712, kshark.GcRoot$NativeStack@b961ee3, kshark.GcRoot$ThreadObject@2e680e0, kshark.GcRoot$JniLocal@4a94f99, kshark.GcRoot$NativeStack@badb25e, kshark.GcRoot$NativeStack@801c63f, kshark.GcRoot$ThreadObject@6e2cb0c, kshark.GcRoot$JniLocal@fb64c55, kshark.GcRoot$NativeStack@a9e966a, kshark.GcRoot$NativeStack@756875b, kshark.GcRoot$ThreadObject@8778bf8, kshark.GcRoot$JniLocal@15c58d1, kshark.GcRoot$NativeStack@a07af36, kshark.GcRoot$NativeStack@66c3e37, kshark.GcRoot$ThreadObject@c956fa4, kshark.GcRoot$JniLocal@7cb10d, kshark.GcRoot$NativeStack@1788c2, kshark.GcRoot$NativeStack@dbc86d3, kshark.GcRoot$ThreadObject@48be210, kshark.GcRoot$JniLocal@6d05109, kshark.GcRoot$NativeStack@5596f0e, kshark.GcRoot$NativeStack@9bebd2f, kshark.GcRoot$ThreadObject@5350f3c, kshark.GcRoot$JniLocal@6a3f4c5, kshark.GcRoot$NativeStack@dc16e1a, kshark.GcRoot$NativeStack@d03fd4b, kshark.GcRoot$ThreadObject@4e1e328, kshark.GcRoot$JniLocal@541841, kshark.GcRoot$NativeStack@67851e6, kshark.GcRoot$NativeStack@8132327, kshark.GcRoot$ThreadObject@c0609d4, kshark.GcRoot$JniLocal@388f77d, kshark.GcRoot$NativeStack@467a672, kshark.GcRoot$NativeStack@304cac3, kshark.GcRoot$ThreadObject@4a3ef40, kshark.GcRoot$JniLocal@8328e79, kshark.GcRoot$NativeStack@f85b7be, kshark.GcRoot$NativeStack@4df501f, kshark.GcRoot$ThreadObject@478bf6c, kshark.GcRoot$JniLocal@b449935, kshark.GcRoot$NativeStack@6e191c

proguardMapping=null,

bytesForClassSize=2,

bytesForInstanceSize=2,

bytesForObjectArraySize=3,

bytesForPrimitiveArraySize=3,

useForwardSlashClassPackageSeparator=false,

classFieldsReader=kshark.internal.ClassFieldsReader@f795994,

classFieldsIndexSize=3,

classCount=4940,

instanceCount=90258,

objectArrayCount=6431,

primitiveArrayCount=67943