package danta.controller;
import java.io.File;
import javax.inject.Inject;

import danta.domain.product.Product;
import danta.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("shop/product/*")
public class ProductController {

    @Inject
    ProductService productService;

    // 1. 상품 전체 목록
    @RequestMapping("/list.do")
    public ModelAndView list(ModelAndView mav) {
        mav.setViewName("shop/productList");
        mav.addObject("list", productService.listProduct());
        return mav;
    }
    // 2. 상품 상세보기
    @RequestMapping("detail/{productId}")
    public ModelAndView detail(@PathVariable("productId") int productId, ModelAndView mav){
        mav.setViewName("/shop/productDetail");
        mav.addObject("vo", productService.detailProduct(productId));
        return mav;
    }

    // 3. 상품등록 페이지 매핑
    @RequestMapping("write.do")
    public String write(){
        return "/shop/productWrite";
    }

    // 4. 상품등록 처리 매핑
    @RequestMapping("insert.do")
    public String insert(Product product){
        String filename = "";
        // 첨부파일(상품사진)이 있으면
        if(!product.getProductPhoto().isEmpty()){
            filename = product.getProductPhoto().getOriginalFilename();
            // 개발디렉토리 - 파일 업로드 경로
            //String path = "C:\\Users\\doubles\\Desktop\\workspace\\gitSpring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images"; //
            // 배포디렉토리 - 파일 업로드 경로
            String path = "C:\\Users\\doubles\\Desktop\\workspace\\spring\\"
                    + "\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
                    + "tmp0\\wtpwebapps\\spring02\\WEB-INF\\static\\img\\";
            try {
                new File(path).mkdirs(); // 디렉토리 생성
                // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                product.getProductPhoto().transferTo(new File(path+filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
            product.setProductUrl(filename);
            productService.insertProduct(product);
        }
        return "redirect:/shop/product/list.do";
    }

    // 5. 상품 수정(편집) 페이지 매핑
    @RequestMapping("edit/{productId}")
    public ModelAndView edit(@PathVariable("productId") int productId, ModelAndView mav){
        mav.setViewName("/shop/productEdit");
        mav.addObject("vo", productService.detailProduct(productId));
        return mav;
    }

    // 6. 상품 수정(편집) 처리 매핑
    @RequestMapping("update.do")
    public String update(Product product){
        String filename = "";
        // 첨부파일(상품사진)이 변경되면
        if(!product.getProductPhoto().isEmpty()){
            filename = product.getProductPhoto().getOriginalFilename();
             //개발디렉토리 - 파일 업로드 경로
            String path = "C:\\Users\\doubles\\Desktop\\workspace\\gitSpring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images"; //
            // 배포디렉토리 - 파일 업로드 경로
//            String path = "C:\\Users\\doubles\\Desktop\\workspace\\spring\\"
//                    + "\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
//                    + "tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
            try {
                new File(path).mkdirs(); // 디렉토리 생성
                // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                product.getProductPhoto().transferTo(new File(path+filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
            product.setProductUrl(filename);
            // 첨부파일이 변경되지 않으면
        } else {
            // 기존의 파일명
            Product product2 = productService.detailProduct(product.getProductId());
            product.setProductUrl(product2.getProductUrl());
        }
        productService.updateProduct(product);
        return "redirect:/shop/product/list.do";
    }

    // 7. 상품 삭제 처리 매핑
    @RequestMapping("delete.do")
    public String delete(@RequestParam int productId){
        // 상품 이미지 정보
        String filename = productService.fileInfo(productId);
        String path = "C:\\Users\\doubles\\Desktop\\workspace\\spring\\"
                + "\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
                + "tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
        // 상품 이미지 삭제
        if(filename != null){
            File file = new File(path+filename);
            // 파일이 존재하면
            if (file.exists()){
                file.delete(); // 파일 삭제
            }
        }
        // 레코드 삭제
        productService.deleteProduct(productId);


        return "redirect:/shop/product/list.do";
    }
}

