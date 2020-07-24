package com.wicresoft.ghealth.common;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class MyXMLWorkerHelper {
    public static class MyFontsProvider extends XMLWorkerFontProvider {
        public MyFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {

            String fntname = fontname;
            if (fntname == null) {
                fntname = "宋体";
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

    public static ElementList parseToElementList(String html, String css) throws BusinessException {
        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        if (css != null) {
            CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(css.getBytes()));
            cssResolver.addCss(cssFile);
        }

        // HTML
        MyFontsProvider fontProvider = new MyFontsProvider();
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.autoBookmark(false);

        // Pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, end);
        CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, htmlPipeline);

        // XML Worker
        XMLWorker worker = new XMLWorker(cssPipeline, true);
        XMLParser p = new XMLParser(worker);
        html = html.replace("<hr>", "").replace("<img>", "").replace("<param>", "")
                .replace("<link>", "");
        //html = "<div style='color:green;font-size:20px;'>你好世界！hello world !</div>";


        try {
			p.parse(new ByteArrayInputStream(html.getBytes()));
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return elements;
    }
    
    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/HelloWorld.pdf"));
            document.open();
            String html = "<h4 data-v-2614806d=\"\"><strong data-v-2614806d=\"\">RESULTS体内毒素检测的结果 - 张一：</strong></h4><p><br></br></p><table width=\"90%\"><tbody><tr><td class=\"tbTitle\" colspan=\"2\">Liquid Chromatography液相色普法<br></br></td></tr><tr><td class=\"tdTitle\">Sample Type样本类型</td><td class=\"tdTitle\">Xenobiotics Detected毒素检测</td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">Hair 头发</strong><br></br></td><td class=\"tdValue\"><strong><strong data-v-2614806d=\"\">Gentamicin 庆大霉素 <br data-v-2614806d=\"\"></br>Caffeine 咖啡因</strong><br></br></strong></td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">Fingernails 手指甲</strong><br></br></td><td class=\"tdValue\"><strong><strong data-v-2614806d=\"\">Gentamicin 庆大霉素 <br data-v-2614806d=\"\"></br>Caffeine 咖啡因</strong><br></br></strong></td></tr></tbody></table><p><br></br></p><table width=\"90%\"><tbody><tr><td class=\"tbTitle\" colspan=\"2\">Gas Chromatography/Mass Spectrometry (GC / MS)<br></br>气相色谱质谱联用<br></br></td></tr><tr><td class=\"tdTitle\">Sample Type样本类型</td><td class=\"tdTitle\">Xenobiotics Detected毒素检测</td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">Hair 头发</strong><br></br></td><td class=\"tdValue\"><strong><strong data-v-2614806d=\"\">Squalene 鲨烯 <br data-v-2614806d=\"\"></br>3-(4-Chlorophenoxy)-1,2-propanediol</strong><br></br></strong></td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">Fingernails 手指甲</strong><br></br></td><td class=\"tdValue\"><strong><strong data-v-2614806d=\"\">Squalene 鲨烯 <br data-v-2614806d=\"\"></br>3-(4-Chlorophenoxy)-1,2-propanediol</strong><br></br></strong></td></tr></tbody></table><p><br></br></p><table width=\"90%\"><tbody><tr><td class=\"tbTitle_Extraordinary\">异常项</td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">4-Indol-3-Ylbutyric Acid</strong><br></br></td></tr><tr><td class=\"tdText\"><strong data-v-2614806d=\"\">2,4-D (Sodium Salt)</strong><br></br></td></tr></tbody></table><p><br></br></p><h4 data-v-2614806d=\"\"><strong data-v-2614806d=\"\">EXPERT OPINION专家治疗建议：</strong></h4><p><br></br></p><p data-v-2614806d=\"\">身体未能完全代谢鲨烯、庆大霉素、和咖啡因。建议检测肝肾排毒功能，肿瘤早期筛查。建议进行人体毒素净化和法国CRAM原细胞疗法来提高细胞更新换代的能力和活力，全面改善人体各项生理机能。</p><p><br></br></p><h4 data-v-2614806d=\"\"><strong data-v-2614806d=\"\">适宜食物：</strong></h4><p><br></br></p><p data-v-2614806d=\"\">主食及豆类：大米、小米、粳米、糙米、玉米面、小麦面粉、燕麦、黑 米、黄豆、红豆、黑豆、红薯等米面杂粮、豆制品及薯类是能量、B 族 维生素和食物纤维的主要来源。</p><p data-v-2614806d=\"\">肉蛋奶：猪瘦肉、牛肉、羊肉、牡蛎、青虾、虾皮、鸡蛋、牛奶。</p><p data-v-2614806d=\"\">蔬菜：西红柿、油菜、芹菜、茄子、笋、胡萝卜、毛豆、蘑菇、海带、 发菜、木耳、紫菜、蒜苗、白菜、甜椒等。</p><p data-v-2614806d=\"\">水果：苹果、香蕉、樱桃、葡萄、草莓、橘子等，还有核桃、桂圆肉、 杏仁、花生、芝麻、芝麻酱等。</p><p data-v-2614806d=\"\"><br></br></p>";
            		ElementList elementList;
					try {
						elementList = parseToElementList(html, null);
						Paragraph context = new Paragraph();
						 for (Element element : elementList) {
				             context.add(element);
				             System.out.println("*********&&&&&"+element);
				         }
						document.add(context);
						 
						 //document.add(new Paragraph("A Hello World PDF document."));
				            document.close();
				            writer.close();
					} catch (BusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
            
            
            
            
            
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
