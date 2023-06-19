$(function () {
    // 바로 구매 ====================
    function getCartInputList() {
        let cartInputList = "";
        let productId = $("#productId\\.Input").val();
        let orderCount = $("#orderCountInput").val();
        cartInputList += "<input type='number' name='orderLineList[0].productId' value='" + productId + "'>";
        console.log("productId =",productId)
        cartInputList += "<input type='number' name='orderLineList[0].orderCount' value='"+ orderCount + "'>";
        console.log("orderCount =",orderCount)

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