/**
 * 상품등록 페이지
 */
$(document).ready(function(){
    // 상품 등록 유효성검사
    $("#addBtn").click(function(){
        var productName = $("#productName").val();
        var productPrice = $("#productPrice").val();
        var productDesc = $("#productDesc").val();
        var productPhoto = $("#productPhoto").val();

        if(productName == "") {
            alert("상품명을 입력해주세요");
            productName.foucs();
        } else if (productPrice == "") {
            alert("상품 가격을 입력해주세요");
            productPrice.focus();
        } else if (productDesc == "") {
            alert("상품 설명을 입력해주세요");
            productDesc.focus();
        } else if (productPhoto == "") {
            alert("상품 사진을 입력해주세요");
            productPhoto.focus();
        }
        // 상품 정보 전송
        document.form1.action = "${path}/shop/product/insert.do";
        document.form1.submit();
    });
    // 상품 목록이동
    $("#listBtn").click(function(){
        location.href='${path}/shop/product/list.do';
    });
});

/**
 * 상품목록 페이지
 */
$(document).ready(function(){
    $("#btnAdd").click(function(){
        location.href="${path}/shop/product/write.do";
    });
});

/**
 * 상품 업데이트 페이지
 */
$(document).ready(function(){
    // 상품 수정 버튼 클릭이벤트
    $("#editBtn").click(function(){
        var productName = $("#productName").val();
        var productPrice = $("#productPrice").val();
        var productDesc = $("#productDesc").val();
        // 상품 수정 폼 유효성 검사
        if(productName == "") {
            alert("상품명을 입력해주세요");
            productName.foucs();
        } else if (productPrice == "") {
            alert("상품 가격을 입력해주세요");
            productPrice.focus();
        } else if (productDesc == "") {
            alert("상품 설명을 입력해주세요");
            productDesc.focus();
        }
        document.form1.action = "${path}/shop/product/update.do";
        document.form1.submit();
    });
    // 상품 삭제 버튼 클릭이벤트
    $("#deleteBtn").click(function(){
        // 상품 삭제 확인
        if(confirm("상품을 삭제하시겠습니까?")){
            document.form1.action = "${path}/shop/product/delete.do";
            document.form1.submit();
        }
    });
    // 상품 목록 버튼 클리이벤트
    $("#listBtn").click(function(){
        location.href = "${path}/shop/product/list.do";
    });
});