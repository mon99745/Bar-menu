$(function() {
    // 주문 요청 폼생성 ====================
    // 선택된 input dom을 만들어 반환
    function getCartInputList() {
        let trList = $(".orderItemListTr");
        let orderLineInputList = "";

        // 테이블의 형태에 따라서 시작 인덱스(i)의 값이 유동적
        for (let i = 0; i < trList.length; i++) {
            let itemId = $(trList[i]).find(".itemIdTd").text();
            let orderCount = $(trList[i]).find(".orderCountTd").text();
            orderLineInputList += "<input type='text' name='orderLineList[" + i + "].itemId'  value='" + itemId +"'> ";
            orderLineInputList += "<input type='text' value='" + orderCount + "' name='orderLineList[" + i + "].orderCount'> ";
        }

        return orderLineInputList;
    }

    // form 생성
    $("#payBtn").on("click", function () {
        let form = $("<form action='/order/order' method='post'>" +
            getCartInputList() +
            "</form> ");
        $("body").append(form);
        form.submit();
    });
});