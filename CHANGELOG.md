Changelog
=========

## Version 1.0.0 *(In development)*
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
