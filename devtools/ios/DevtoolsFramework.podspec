Pod::Spec.new do |spec|
  spec.name         = "DevtoolsFramework"
  spec.version      = "0.0.1"
  spec.summary      = "DevtoolsFramework."
  spec.description  = "Multiplatform dev tools config library"
  spec.homepage     = "https://github.com/maximbircu/devtools-library"
  spec.license      = "Apache License 2.0"
  spec.author       = { "Alexandr Vdovicenco" => "vdovicenco.alexandr@gmail.com" }
  spec.source       = {
    :git => "git@ASV44.github.com:maximbircu/devtools-library.git",
    :branch => "14-set-up-ios-modules"
  }
  spec.ios.deployment_target  = "10.0"
  spec.source_files  = "devtools/ios/DevtoolsFramework/**/*.swift"
  spec.vendored_frameworks = 'devtools/ios/Frameworks/devtools.framework'
end
