function validateOrderCount() {
    let orderCount = parseInt(document.getElementById("orderCountInput").value);

    if(orderCount <= 0) {
        alert("주문 수량은 1개 이상이어야 합니다.");
        return false;
    }
    return true;
}