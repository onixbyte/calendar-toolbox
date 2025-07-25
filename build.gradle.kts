/*
 * Copyright (c) 2024-2025 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.net.URI

plugins {
    java
    id("java-library")
    id("maven-publish")
    id("signing")
}

val artefactVersion: String by project

group = "com.onixbyte"
version = artefactVersion

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Jar> {
    exclude("logback.xml")
}

dependencies {
    compileOnly(libs.slf4j.api)
    implementation(libs.logback.classic)
    api(platform(libs.onixbyte.versionCatalogue))
    api(libs.onixbyte.commonToolbox)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("calendarToolbox") {
            groupId = group.toString()
            artifactId = "calendar-toolbox"
            version = artefactVersion

            pom {
                name = "OnixByte Calendar Toolbox"
                description = "The calendar creator (RFC5545) of OnixByte toolbox."
                url = "https://onixbyte.com/projects/calendar-toolbox"

                licenses {
                    license {
                        name = "MIT"
                        url = "https://onixbyte.com/projects/calendar-toolbox/license"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com:onixbyte/onixbyte-toolbox.git"
                    developerConnection = "scm:git:git://github.com:onixbyte/onixbyte-toolbox.git"
                    url = "https://github.com/onixbyte/calendar-toolbox.git"
                }

                developers {
                    developer {
                        id = "zihluwang"
                        name = "Zihlu Wang"
                        email = "really@zihlu.wang"
                        timezone = "Asia/Hong_Kong"
                    }

                    developer {
                        id = "siujamo"
                        name = "Siu Jam'o"
                        email = "jamo.siu@outlook.com"
                        timezone = "Asia/Shanghai"
                    }
                }
            }

            from(components["java"])

            signing {
                sign(publishing.publications["calendarToolbox"])
            }
        }

        repositories {
            maven {
                name = "sonatypeNexus"
                url = URI(providers.gradleProperty("repo.maven-central.host").get())
                credentials {
                    username = providers.gradleProperty("repo.maven-central.username").get()
                    password = providers.gradleProperty("repo.maven-central.password").get()
                }
            }
        }
    }
}