$(function () {
    // 바로 구매 ====================
    function getCartInputList() {
        let cartInputList = "";
        let itemId = $("#itemIdInput").val();
        let orderCount = $("#orderCountInput").val();
        cartInputList += "<input type='text' name='orderLineList[0].itemId' value='" + itemId + "'>";
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