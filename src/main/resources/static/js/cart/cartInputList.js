$(function () {
    // 바로 구매 ====================
    function getCartInputList() {
        let cartInputList = "";
        let productId = $("#productIdInput").val();
        let orderCount = $("#orderCountInput").val();
        cartInputList += "<input type='number' name='orderLineList[0].productId' value='" + productId + "'>";
        cartInputList += "<input type='text' name='orderLineList[0].orderCount' value='"+ orderCount + "'>";
        return cartInputList;
    }

    $("#orderBtn").on("click", function() {
        let form = $("<form action='/order/direct' method='post'>" +
            getCartInputList() +
            "</form> ");
        $("body").append(form);
        form.submit();
    });

});