# Hexdit

A Hexo blog editor built on electron and reagent

## Running

```shell
npm install electron-prebuilt -g

lein cooper
electron .
```

## Releasing

```shell
lein do clean, less once, cljsbuild once renderer-release, cljsbuild once main-release
electron .
```

## Building

```shell
npm install electron-packager -g
electron-packager . HelloWorld --platform=darwin --arch=x64
```
