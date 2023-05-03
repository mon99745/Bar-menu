$(function() {
    const size = 20;
    let total = 0;

    if($(window).scrollTop() + $(window).height() == $(document).height()) {
        loadMoreOrderList();
    }
    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() == $(document).height()) {
            loadMoreOrderList();
        }
    });

    async function loadMoreOrderList() {
        let nextPage = parseInt(total/size);
        let data = '';

        await $.get(`/api/v1/order?page=${nextPage}&size=${size}`, function(result) {
            data = result;
        })
        $("tbody").append(toTrList(data.myOrderList));
        total += data.total;
        if (data.total < size) {
            $(window).unbind();
        }
    }

    function toTrList(orderList) {
        let trList = "";

        orderList.forEach(order => {
            let orderDate = formatOrderDate(order);

            trList +=
                "<tr>" +
                `<td>${order.orderId}</td>` +
                `<td>${orderDate}</td>` +
                "<td>" +
                "<a class='itemInformationContainer' href='/my/order/" + order.orderId + "'>" +
                "<div class='representativeImageContainer'>" +
                "<img class='representativeImage' src='" + order.representativeImagePath + "'>" +
                "</div>" +
                "<span class='representativeName'>" + order.representativeItemName + " </span>" +
                "</a>" +
                "</td>" +
                "<td>" + order.totalAmount + "</td>" +
                "<td>" +
                "<div>" +
                "<span>" + order.orderStatus + "</span>" +
                "</div>" +
                "<div>" +
                "<a href=" + `/my/order/${order.orderId}` + "><span>결제 조회</span></a>"
            "</div>" +
            "</td>" +
            "</tr>";
        });

        function formatOrderDate(order) {
            let splitOrderDate = order.orderDate.split("T");
            let ymd = splitOrderDate[0];
            let splitTime = splitOrderDate[1].split(":");
            let time = splitTime[0] + ":" + splitTime[1];
            let orderDate = ymd + " " + time;
            return orderDate;
        }

        return trList;
    }
});