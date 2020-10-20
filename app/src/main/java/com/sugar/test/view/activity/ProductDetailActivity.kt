package com.sugar.test.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import com.sugar.test.R
import com.sugar.test.data.model.ContentData
import com.sugar.test.data.model.Product
import com.sugar.test.databinding.ActivityProductScreenBinding
import com.sugar.test.utility.AppConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.sliderLayout
import kotlinx.android.synthetic.main.activity_product_screen.*
import kotlinx.android.synthetic.main.common_arrow_layout.*

@AndroidEntryPoint
class ProductDetailActivity: BaseActivity<ActivityProductScreenBinding>(),BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    companion object {
        fun getCallingIntent(context: Context, data: Product): Intent {
            val bundle = Bundle()
            bundle.putSerializable(AppConstant.BUNDLE_PRODUCT_DATA, data)
            return Intent(context, ProductDetailActivity::class.java).putExtras(bundle)
        }
    }

    private fun getBundleData() {
        if (intent.hasExtra(AppConstant.BUNDLE_PRODUCT_DATA)) {
            product = (intent.extras?.getSerializable(AppConstant.BUNDLE_PRODUCT_DATA) as Product?)!!
        }
    }

    lateinit var product: Product

    override val layoutId: Int
        get() = R.layout.activity_product_screen

    override fun init() {
        getBundleData()
        setListener()
        setObserver()
    }

    private fun setListener() {
        var sliderImages = ArrayList<String>()
        var contentData1 = ContentData()
        contentData1.mediaUrl = "https://d32baadbbpueqt.cloudfront.net/dashboard/1602700732Festive-Glam-Kit-Mobile-Homepage-Banner.jpg"
        contentData1.redirectUrl = "https://in.sugarcosmetics.com/products/festive-glam-kit"
        sliderImages.add("https://d32baadbbpueqt.cloudfront.net/dashboard/1602700732Festive-Glam-Kit-Mobile-Homepage-Banner.jpg")
        var contentData2 = ContentData()
        contentData2.mediaUrl = "https://d32baadbbpueqt.cloudfront.net/dashboard/1602442711MAH--Homepage-banner_600x500_App.gif"
        contentData2.redirectUrl = "https://in.sugarcosmetics.com/products/matte-as-hell-crayon-lipstick"
        sliderImages.add("https://d32baadbbpueqt.cloudfront.net/dashboard/1602700732Festive-Glam-Kit-Mobile-Homepage-Banner.jpg")
        var contentData3 = ContentData()
        contentData3.mediaUrl = "https://d32baadbbpueqt.cloudfront.net/dashboard/1602233360Lockdown-wedding-makeup-look---Mobile-banner.jpg"
        contentData3.redirectUrl = "https://blog.sugarcosmetics.com/quick-makeup-look-for-post-lockdown-weddings/"
        sliderImages.add("https://d32baadbbpueqt.cloudfront.net/dashboard/1602233360Lockdown-wedding-makeup-look---Mobile-banner.jpg")

        for (name in sliderImages) {
            val textSliderView = TextSliderView(this)
            textSliderView
                .image(name)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this)
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                .putString("extra", name)
            sliderLayout.addSlider(textSliderView)
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion)
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        sliderLayout.setCustomAnimation(DescriptionAnimation())
        sliderLayout.setDuration(3000)
        sliderLayout.addOnPageChangeListener(this@ProductDetailActivity)

        tv_product_name.text = product.title
        if (!product.variants.isNullOrEmpty()) {
            tv_price.text = product.variants!!.get(0).compare_at_price.toString()
        }
        tv_variant.text = "Variants"


        tv_description.text = Html.fromHtml(Html.fromHtml(product.body_html).toString())

        img_back_arrow.setOnClickListener {
            finish()
        }

    }

    private fun setObserver() {

    }

    override fun onSliderClick(slider: BaseSliderView?) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }
}