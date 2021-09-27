Changelog
=========

## *(In development)*


## Version 1.0.1 *2021-09-26*
Issue: [#56](https://github.com/maximbircu/devtools-library/issues/56)
1. Got rid of Jcenter dep
2. Adjusted maven publish logic so that the whole project will be published at once and there'll be no need to upload the modules separately.
3. Implement `release-android.yml` that runs all required checks and uploads the library to Maven Central.

Issue: [#73](https://github.com/maximbircu/devtools-library/issues/73)
- Implemented a discard dialog - in case the user will try to leave the configuration screen with unsaved changes the screen will not close and a discard dialog will be displayed instead. The dialog will be closed and the unsaved changes discarded in case the user will hit the OK button.
- Updated some libraries including kotlinx-serialization and migrated to AGP 7
- Update targetSdkVersion to 31
- Update to latest ktlint
- Update to latest detekt

Issue: [#12](https://github.com/maximbircu/devtools-library/issues/12)
- Implement iOS YAML devtools reader

Issue: [#15](https://github.com/maximbircu/devtools-library/issues/15)
- Add iOS implementation of toggle tool
- Add intercepting of view from storyboard for replacing it with devtool with specific devtool element

Issue: [#68](https://github.com/maximbircu/devtools-library/issues/66)
- Enable dev tool automatically whenever the user tries to update its configuration value

Issue: [#66](https://github.com/maximbircu/devtools-library/issues/66)
- Replace the usage of GITHUB_ACCESS_TOKEN with the standard GITHUB_TOKEN secret
- Update targetSdk to 30
- Update material to 1.2.1
- Update appcompat to 1.2.0
- Update constraints layout to 2.1.0-alpha2
- Update core-ktx to 1.3.2
- Update snakeyaml to 1.27

Issue: [#62](https://github.com/maximbircu/devtools-library/issues/62)
- Fix spelling mistakes in issues templates files
- Add maven central badges and fill in the "Add the library dependency" section from the QuickStart docs;

Issue: [#64](https://github.com/maximbircu/devtools-library/issues/64)
- Update Kotlin to `1.3.71`
- Rename `com.maximbircu.devtools.android.presentation.tools.enum` package to `*.enumtool`
- Migrate from deprecated Kotlin Synthetics to Android ViewBinding
- Update Gradle to 6.7.1 and AGP to 4.1.0

## Version 1.0.0 06/05/2020
Issue: [#58](https://github.com/maximbircu/devtools-library/issues/58)
- Update the Android library common module dependency for release configuration to 1.0.0

Issue: [#41](https://github.com/maximbircu/devtools-library/issues/41)
- Configure maven-publish, signing, and dokka plugins

Issue: [#50](https://github.com/maximbircu/devtools-library/issues/50)
- Use same container for both compact and current value views

Issue: [#49](https://github.com/maximbircu/devtools-library/issues/49)
- Hide enable/disable the checkbox for group tools

Issue: [#40](https://github.com/maximbircu/devtools-library/issues/40)
- Cover the whole library with documentation, check README;
- Add library extension Android samples;
- Remove redundant yml- prefix;
- Replace defaultValue with default everywhere to make the API consistent.

Issue: [#39](https://github.com/maximbircu/devtools-library/issues/39)
- Add `DevTools#updateFromParams` which allows the library consumer to update the tools from a set of params(key-value pairs)
- Add `DevTools#updateFromBundle` which allows the Android library consumer to update the tools from an Android bundle
- Made each `DevTools` instance use a different storage key so that the similar tools don't collide

Issue: [#37](https://github.com/maximbircu/devtools-library/issues/37)
- Add DevToolsStorage#getAllConfigAsJson method, which takes a filter parameter and returns a filtered JSON string of configuration values

Issue: [#38](https://github.com/maximbircu/devtools-library/issues/38)
- Add a critical update flag to each dev tool
- Do not invoke update config update in case none of the tools was updated

Issue: [#14](https://github.com/maximbircu/devtools-library/issues/14)
- Set up iOS library module
- Set up iOS sample app module

Issue: [#36](https://github.com/maximbircu/devtools-library/issues/36)
- Add a context menu to all dev tools
- Aad a custom context menu for GroupTool
- Fix number exception crash which happens when passing empty values for number text tools

Issue: [#43](https://github.com/maximbircu/devtools-library/issues/43) [#35](https://github.com/maximbircu/devtools-library/issues/35)
- Add a different EnumTool UX for large options size
- Fix crash when selecting twice the same option

Issue: [#33](https://github.com/maximbircu/devtools-library/issues/33)
- Add an in-memory persistence mechanism

Issue: [#16](https://github.com/maximbircu/devtools-library/issues/16)
- Use macOS machine for the "assemble" Github Action step

Issue: [#28](https://github.com/maximbircu/devtools-library/issues/28)
- Implement JsonSchema reader
- Add a JsonSchema source dev tools configuration screen to the Android sample app
- Add a combined source dev tools configuration screen to the Android sample app

Issue: [#22](https://github.com/maximbircu/devtools-library/issues/22)
- Implement GroupTool, which will to group devtools that belong to the same context
- Add Android view implementation for the GroupTool component

Issue: [#21](https://github.com/maximbircu/devtools-library/issues/21)
- Implement EnumTool which will be able to manipulate enum configuration values
- Add Android view implementation for the EnumTool component

Issue: [#20](https://github.com/maximbircu/devtools-library/issues/20)
- Implement the TimeTool component which will be able to manipulate time configuration values

Issue: [#17](https://github.com/maximbircu/devtools-library/issues/17)
- Implement the TextTool component which will be able to manipulate text configuration values
- Add Android view implementation for the TextTool component
- Downgrade Gradle version to 3.6.0 (Otherwise is not possible to open the project in a non-canary AS)

Issue: [#11](https://github.com/maximbircu/devtools-library/issues/11)
- Update Kotlin to `1.3.71`
- Update to Gradle `4.1.0-alpha04`
- Split master and PR GitHub actions workflow
- Make JaCoCo run on all modules
- Cover all android module code with unit tests
- Add YML dev tools input source support
- Add material design and adjust the UI

Issue: [#6](https://github.com/maximbircu/devtools-library/issues/6)
- Add preferences tool store interface with implementations for both iOS and Android platforms
- Increase code coverage
- Fix and adjust KDocs
- Adjust Detekt configuration excluding test files from several rules
- Update PR description template
- Disable `import-ordering` Ktlint rule

Issue: [#7](https://github.com/maximbircu/devtools-library/issues/7)
- Add branch name and commit message style checks using [github-action-pull-request-checkstyle](https://github.com/maximbircu/github-action-pull-request-checkstyle/tags)
- Split CI steps to different jobs to increase the build type

Issue: [#5](https://github.com/maximbircu/devtools-library/issues/5)
- Integrate codecov to the main workflow
- Adjust CI steps names

Issue: [#3](https://github.com/maximbircu/devtools-library/issues/3)
- Implement DevTool skeleton
- Implement ToggleTool, a dev tool to manipulate boolean configuration values.
- Configure android lint, ktlint, and detekt
- Cover everything with unit tests
- Adjust CI to run check-style and tests
- Configure iOS targets

Issue: [#1](https://github.com/maximbircu/devtools-library/issues/1)
- Set up a new multi-platform project for the dev tools library
- Add .github templates for the PR description and issues
- Setup a simple CI with GitHub actions
