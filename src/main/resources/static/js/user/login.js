
let index = {
    //초기화
    init: function () {
        //회원가입
        //this를 바인딩하기 위해 화샬표 함수 사용
        $("#btn-save").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원가입 안됨")
            } else {
                this.save();
            }
        });
    },

    //회원가입
    login: function() {
        let data = { //JavaScript Object
            username: $("#username").val(),
            password: $("#password").val()
        }

        $.ajax({
            type: "POST", //Http method
            url: "/auth/api/v1/create",
            data: JSON.stringify(data), //JSON으로 변환
            contentType: "application/json; charset=utf-8", //MIME 타입
            dataType: "json" //응답 데이터
        }).done(function () {
            alert("회원가입이 완료되었습니다.");
            location.href = "/auth/user/login";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }
}
index.init();