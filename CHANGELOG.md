Changelog
=========

## Version 1.0.0 *(In development)*

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
- Adjust CI to run check-style and tests.
- Configure iOS targets

Issue: [#1](https://github.com/maximbircu/devtools-library/issues/1)
- Set up a new multi-platform project for the dev tools library
- Add .github templates for the PR description and issues 
- Setup a simple CI with GitHub actions. 
