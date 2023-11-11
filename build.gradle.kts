plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.withType<Jar>() {
    configurations["compileClasspath"].forEach{file: File ->
        from(zipTree(file.absoluteFile))
    }
    configurations["testCompileClasspath"].forEach{file: File ->
        from(zipTree(file.absoluteFile))
    }
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "org.example.MyName"
        )
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

