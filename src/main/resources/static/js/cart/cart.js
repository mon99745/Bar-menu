$(function () {
    // 장바구니 수량변경 ====================
    $(".orderCountModifyBtn").on("click", function () {
        let itemId = $(this).parent().find(".itemId").val();
        let newOrderCount = $(this).parent().find(".orderCount").val();

        let formData = new FormData();
        formData.append("itemId", itemId);
        formData.append("orderCount", newOrderCount);

        $.ajax({
            url: '/carts',
            data: formData,
            processData: false,
            contentType: false,
            type: 'PUT',
            success: function (data) {
                location.reload();
            }
        });
    })

    // 장바구니 삭제 요청 ====================
    $(".removeCartItemBtn").on("click", function () {
        let itemId = $(this).parent().find(".itemId").val();

        $.ajax({
            url: `/carts?itemId=${itemId}`,
            type: 'DELETE',
            success: function (data) {
                location.reload();
            }
        });
    })

    // 전체 체크박스 컨트롤 ===================
    $("#checkAll").click(function(){
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    // 주문 요청 폼생성 ====================
    // 선택된 input dom을 만들어 반환
    function getCartInputList() {
        let trList = $("tr");
        let cartInputList = "";

        // tr의 첫번째 줄은 head 이므로 1번째 부터
        for (let i = 1; i < trList.length; i++) {
            // 체크된 아이템만 추가하도록
            let isSelected = $(trList[i]).find(".checkBox").is(":checked");
            if (isSelected) {
                let itemId = $(trList[i]).find(".orderCountTd .itemId").val();
                let orderCount = $(trList[i]).find(".orderCountTd .orderCount").val();

                let orderLineListIdx = i-1;
                cartInputList += "<input type='text' name='orderLineList["+ orderLineListIdx + "].itemId' value='" + itemId + "'>";
                cartInputList += "<input type='text' name='orderLineList["+ orderLineListIdx + "].orderCount' value='" + orderCount + "'>"
            }
        }

        if (cartInputList.length == 0) {
            throw alert("한 개 이상의 품목을 선택하세요.");
        }

        return cartInputList;
    }

    // form 생성
    $("#orderBtn").on("click", function () {
        let form = $("<form action='/orders' method='post' style='display: none'>" +
            getCartInputList() +
            "</form> ");
        $("body").append(form);

        form.submit();
    });

});