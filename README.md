# KMM library template focused on integration to existing codebases [![Build and test](https://github.com/wzieba/KMM-in-exsiting-project-template/actions/workflows/readme.yml/badge.svg)](https://github.com/wzieba/KMM-in-exsiting-project-template/actions/workflows/readme.yml)

This template is prepared with thought of seamless integration of KMM library to existing, native codebases.

## Content
- Default KMM project created by Android Studio wizard
- CI workflows based on Github Actions. It builds and tests both Android and iOS variants parallelly
- Release configuration based on Cocoapods (iOS) and Jitpack (Android)
- Static code analysis based on [Ktlint](https://github.com/pinterest/ktlint)
- Automated dependency update via Dependabot and Mergify (optional, only if Mergify is enabled for a repository)

## Setup
After creating repository based on this template, several steps are required in order to publish artifacts for further usage in native codebases:

### Github
1. Use this template to create a new repository
2. Create a second repository for hosting iOS artifact (Cocoapods)

### Local machine
3. Clone repository created from template
4. Go to `shared/build.gradle.kts` and update:
- `iosFrameworkName` - it's name of the artifact that will be hosted via Cocoapods
- `fatFrameworkCocoaConfig`:
    - `gitUrl` to match Cocoapods repository created in point (2)
    - update other fields at your discretion
5. Execute `./gradlew generateCocoaPodRepoForIosFatFramework` - this will create CocoaPod repository for hosting iOS framework.

## How to distribute

### Android
Use [Jitpack.io](http://jitpack.io/) - it's that simple.

### iOS
This template uses [`kmp-fatframework-cocoa` Gradle Plugin](https://github.com/prof18/kmp-fatframework-cocoa) to publish and manage releases to git repository.
To read more about why is it needed and how it's done, please refer to [great article of the same author](https://www.marcogomiero.com/posts/2021/kmp-existing-project/) - Marco Gomiero ([@prof18](https://github.com/prof18))

#### tldr;
- Run `./gradlew publishIosDebugFatFramework` to publish (build & push to repository) the debug version to `develop` branch (of the seperate, Cocoapods-only repository created in point 2 of `How to use` section)
- Run `./gradlew publishIosReleaseFatFramework` to do the same but for `main/master` branch with tag on a commit based on what's in `fatFrameworkCocoaConfig`

## Typical workflow
1. Write a feature in `shared` module
2. Test on sample apps, which are part of this repository
3. If everything's ok - merge to `develop` and release debug version of iOS framework.
4. Test it in existing Android and iOS projects
5. Iterate or release to iOS framework to production/release channel
