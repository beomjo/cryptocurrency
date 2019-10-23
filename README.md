# Cryptocurrency

<br/>

## Used Stack  
#### Paging(PositionalDataSource), androidSvg, Rx, Retrofit, Glide  

<br/>

## Svg Load with Glide  
Svg는 기본 Glide만으로 로드 할 수 없다.  
```
dependencies{
    // glide
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    implementation 'com.github.bumptech.glide:annotations:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
    kapt 'com.github.bumptech.glide:compiler:4.8.0'
}
```  

Clean-> ReBuild -> GlideApp 생성됨  

```
//AppGlideModule을 상속받는 클래스 생성

@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry
            .register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
            .append(InputStream::class.java, SVG::class.java, SvgDecoder())
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
```
Svg 클래스를 위한 의존성 추가  
```
dependencies{
    implementation 'com.caverock:androidsvg:1.2.1'
}
```   

<br/>

`androidsvg library`에서 추가되는 클래스  
- SvgDecoder
- SvgDrawableTranscoder
- SvgSoftwareLayerSetter  

<br/>

1.SvgDecoder  
link를 통해서 읽은 inputStream을 SVG 내부 표현으로 디코딩을 한다는 의미이다.  
정확하지는 않지만 link 즉, url을 통해 읽어들인 다음 inputStream에 담고 이를 통해 SVG로 만든다. 만들 때는 androidsvg 라이브러리를 사용한다.  

<br/>

2.SvgDrawableTranscoder  
1번에서 만든 SVG를 안드로이드에서 사용할 수 있는 PictureDrawable로 변환한다. 이는 Drawable 클래스를 상속받고 있기 때문에 이렇게 변환된 데이터를 안드로이드에서 사용할 수 있다.  

<br/>

3.SvgSoftwareLayerSetter  
3번은 필수가 아닌 선택이다. 하지만, 필수적으로 사용해야 될 것 같다. 왜냐하면 리스너를 구현한 것이기 때문에 이를 통해서 내가 로드하려는 이미지가 성공적으로 로드되었는지, 실패했는지를 확인할 수 있다  

<br/>


## Image  
#### Main Page  
<center><img src="https://user-images.githubusercontent.com/39984656/67359496-3edfc100-f59e-11e9-9653-970efdf2b883.png" width="300" height="450"></center>  

#### Detail Page  
<center><img src="https://user-images.githubusercontent.com/39984656/67359721-0987a300-f59f-11e9-8c13-258b6142afde.png" width="300" height="450"></center>  

#### Web Page  
<center><img src="https://user-images.githubusercontent.com/39984656/67359725-0b516680-f59f-11e9-8646-c5bcdfcdf278.png" width="300" height="450"></center>  
