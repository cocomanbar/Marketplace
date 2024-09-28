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
    }

    // 用于配置不同的构建类型，例如 `release` 或 `debug`
    buildTypes {
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 手动引入
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.github.getActivity:TitleBar:10.5")
    implementation("com.github.getActivity:Toaster:12.6")
    implementation("com.tencent:mmkv:1.3.4")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.greenrobot:eventbus:3.3.1")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
    implementation("com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar")
    implementation("com.scwang.smart:refresh-header-material:2.0.0")
    implementation("com.scwang.smart:refresh-layout-kernel:2.0.0")
    implementation("com.github.zhpanvip:BannerViewPager:3.1.2")
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    implementation("com.github.chrisbanes:PhotoView:2.3.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.android.support:multidex:1.0.3")
    implementation("com.shuyu:GSYVideoPlayer:8.1.2")

}