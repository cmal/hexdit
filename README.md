# Hexdit
A Hexo blog editor built on electron and reagent

## How to Run
```
npm install electron-prebuilt -g
npm install shadow-cljs -g
npm install

npm run dev
electorn .
```

## Release
```
npm run build
electron-packager . Hexdit --platform=darwin --arch=x64 --version=1.4.13
```
