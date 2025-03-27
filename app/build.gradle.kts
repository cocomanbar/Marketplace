plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    // 程序的包名，用于唯一标识应用程序
    namespace = "cn.mama.marketplace"
    // 使用 API 版本 34 进行编译
    compileSdk = 34

    // 默认的应用程序配置模块
    defaultConfig {
        // 应用程序的包名，唯一标识该应用程序
        applicationId = "cn.mama.marketplace"
        // 最低支持的 Android 版本
        minSdk = 26
        // 被设计和测试的目标 Android 版本
        targetSdk = 34
        // 用于标识应用程序版本的整数值，每次发布新版本时必须递增
        versionCode = 1
        // 版本名称，通常是可读的字符串
        versionName = "1.0"

        // 指定用于运行测试的测试仪器运行器。在这里，它是 AndroidJUnitRunner，用于运行基于 JUnit 的测试
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // 配置 debug 打印Log
        buildFeatures {
            buildConfig = true
        }
    }

    // 用于配置不同的构建类型，例如 `release` 或 `debug`
    buildTypes {
        // debug 配置
        debug {
        }

        // `release`配置
        release {
            // 禁用代码混淆，这是一种安全性手段，但在调试和分析时可能会带来困难
            isMinifyEnabled = false
            // 指定 ProGuard 配置文件，用于代码压缩和混淆
            // 代码混淆就是吧代码中的类和方法以及字段等符号重新命名为没有意义的名字，使得反编译之后的代码难以阅读，属于天机工程了属于是。
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 用于配置 Java 编译选项
    compileOptions {
        // 指定源代码的 Java 版本兼容性，这里设置为 Java 8
        sourceCompatibility = JavaVersion.VERSION_1_8
        // 指定生成的字节码的目标 Java 版本兼容性，也设置为 Java 8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // 用于配置 Kotlin 编译选项
    kotlinOptions {
        // 指定生成的 Kotlin 字节码的目标 JVM 版本，这里设置为 JVM 8
        jvmTarget = "1.8"
    }

    // 构建特性的配置
    buildFeatures {
        // 启动Binding机制
        // ActivityMainBinding 是通过 ViewBinding 自动生成的一个绑定类，用于访问 activity_main.xml 布局文件中定义的视图。
        // 这个绑定类不是手动实现的，而是由编译器在编译过程中生成的
        // 启用了 ViewBinding 并编译项目后，编译器会为每个布局文件生成一个对应的绑定类。
        // 例如，对于 activity_main.xml，会生成 ActivityMainBinding 类
        viewBinding = true

        // 启用 Jetpack Compose，这是一种用于构建 Android 用户界面的声明性 UI 工具包
        // compose = true
    }
}

dependencies {
    // 项目创建自带
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Android Jetpack
    // https://developer.android.google.cn/jetpack/androidx/explorer?hl=zh-cn&case=all
    // 使用 WorkManager API 可以轻松地调度那些必须可靠运行的可延期异步任务
    // 通过这些 API，您可以创建任务并提交给 WorkManager，以便在满足工作约束条件时运行
    implementation("androidx.work:work-runtime:2.9.1")
    // 实现一种在应用启动时初始化组件的简单而高效的方法
    implementation("androidx.startup:startup-runtime:1.1.1")
    // 以异步、一致的事务方式存储数据，克服了 SharedPreferences 的一些缺点
    implementation("androidx.datastore:datastore-preferences:1.1.3")
    // 使用 Paging 库，您可以更轻松地在应用的 RecyclerView 中逐步妥善地加载数据
    implementation("androidx.paging:paging-runtime:3.3.2")
    // 生命周期感知型组件可执行操作来响应另一个组件（如 activity 和 fragment）的生命周期状态的变化
    // 这些组件有助于您写出更有条理且往往更精简的代码，这样的代码更易于维护
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    // 将您的应用细分为在一个 Activity 中托管的多个独立屏幕
    implementation("androidx.fragment:fragment-ktx:1.8.3")
    // recyclerview：在您的界面中显示大量数据，同时最大限度减少内存用量
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // GsonFactory
    // Gson 解析容错：https://github.com/getActivity/GsonFactory
    implementation("com.github.getActivity:GsonFactory:9.6")
    // Json 解析框架：https://github.com/google/gson
    implementation("com.google.code.gson:gson:2.11.0")
    // Kotlin 反射库：用于反射 Kotlin data class 类对象，版本号 请修改成当前项目 Kotlin 的版本号
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    // 标题栏：https://github.com/getActivity/TitleBar
    implementation("com.github.getActivity:TitleBar:10.6")
    // Toaster：https://github.com/getActivity/Toaster
    implementation("com.github.getActivity:Toaster:12.6")

    // mmkv：https://github.com/Tencent/MMKV
    implementation("com.tencent:mmkv:1.3.4")
    // eventbus：https://github.com/greenrobot/EventBus
    implementation("org.greenrobot:eventbus:3.3.1")

    // 缺省页框架StateLayout: https://github.com/liangjingkanji/StateLayout
    implementation("com.github.liangjingkanji:StateLayout:1.4.2")

    // 刷新框架 https://github.com/scwang90/SmartRefreshLayout
    implementation("io.github.scwang90:refresh-layout-kernel:3.0.0-alpha")      //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:3.0.0-alpha")    //经典刷新头
    implementation("io.github.scwang90:refresh-footer-classics:3.0.0-alpha")    //经典加载

    // 搭配recyclerview的适配器：https://github.com/liangjingkanji/BRV
    implementation("com.github.liangjingkanji:BRV:1.6.1")

    // 特定功能依赖
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
    implementation("com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar")
    implementation("com.github.zhpanvip:BannerViewPager:3.1.2")
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    implementation("com.github.chrisbanes:PhotoView:2.3.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.android.support:multidex:1.0.3")
    implementation("com.shuyu:GSYVideoPlayer:8.1.2")
}