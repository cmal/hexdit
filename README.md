# Hexdit

A hexo blog client build with electron and re-frame

## Running it

```shell
npm install electron-prebuilt -g # install electron binaries

lein cooper                      # compile cljs and start figwheel
electron .                       # start electron from another terminal
```

## Releasing

```shell
lein do clean, cljsbuild once renderer-release, cljsbuild once main-release
electron . # start electron to test that everything works
```

After that you can follow [distribution guide for the electron.](https://github.com/atom/electron/blob/master/docs/tutorial/application-distribution.md)

The easiest way to package an electron app is by using [electron-packager](https://github.com/maxogden/electron-packager):

```shell
npm install electron-packager -g                                            # install electron packager
electron-packager . HelloWorld --platform=darwin --arch=x64 --version=1.4.8 # package it!
```
