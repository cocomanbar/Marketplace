// 插件仓库
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// 第三方仓库
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 阿里镜像
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        // JitPack 远程仓库：https://jitpack.io
        maven { setUrl("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Marketplace"
include(":app")
