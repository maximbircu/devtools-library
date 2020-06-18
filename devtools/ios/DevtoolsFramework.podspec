Pod::Spec.new do |spec|
  spec.name         = "DevtoolsFramework"
  spec.version      = "0.0.1"
  spec.summary      = "DevtoolsFramework."
  spec.description  = "Multiplatform dev tools config library"
  spec.homepage     = "https://github.com/maximbircu/devtools-library"
  spec.license      = "Apache License 2.0"
  spec.author       = { "Alexandr Vdovicenco" => "vdovicenco.alexandr@gmail.com" }

  spec.swift_version          = "5.0"
  spec.ios.deployment_target  = "10.0"

  spec.source = {
    :git => "git@github.com:maximbircu/devtools-library.git",
    :tag => "#{spec.version}"
  }
  # TODO Change paths before publishing.
  spec.source_files = "DevtoolsFramework/**/*.swift" # "devtools/ios/DevtoolsFramework/**/*.swift"
  spec.resources = 'DevtoolsFramework/**/*.{xib,xcassets}' # "devtools/ios/DevtoolsFramework/**/*.xib"
  spec.vendored_frameworks = "Frameworks/devtools.framework" # "devtools/ios/Frameworks/devtools.framework"
end
