// // 회원가입에 대한 CRUD 해당하는 JS
'use strict';

let index = {
    //초기화
    init: function () {
        //회원가입
        //this를 바인딩하기 위해 화샬표 함수 사용
        $("#btn-save").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원가입 문제 발생")
            } else {
                this.save();
            }
        });

        //회원 정보 수정
        $("#btn-update").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원수정 문제 발생")
            } else {
                this.update();
            }
        });

        //회원 정보 삭제
        $("#btn-delete").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원삭제 안됨")
            } else {
                this.delete();
            }
        });
    },

    //회원가입
    save: function() {
        let data = { //JavaScript Object
            username: $("#username").val(),
            password: $("#password").val(),
            name: $("#name").val()
        }
        // /auth/user/login
        $.ajax({
            type: "POST", //Http method
            url: "/auth/api/v1/create", //추가 /auth
            data: JSON.stringify(data), //JSON으로 변환
            contentType: "application/json; charset=utf-8", //MIME 타입
            dataType: "json" //응답 데이터 타입
        }).done(function(response) {
            console.log(response);
            alert("회원가입이 완료되었습니다.");
            location.href = "/auth/user/login";
        }).fail(function(xhr, status, error) {
            if(xhr.responseJSON && xhr.responseJSON.message) {
                alert(xhr.responseJSON.message);
            } else {
                alert("회원가입 중 문제가 발생하였습니다.");
                console.error(xhr, status, error);
            }
        });
    },

    //회원 정보 수정
    update: function () {
        let data = {
            authId: $("#authId").val(),
            password: $("#password").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/v1/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

    //회원 정보 삭제
    // 삭제같은 경우는 ID 데이터만 필요
    // input 태그가 아니기 때문
    delete: function () {
        let authId = $("#authId").val();

        $.ajax({
            type: "DELETE",
            url: "/api/v1/user/" + authId,
            dataType: "json"
        }).done(function (res) {
            alert("회원탈퇴가 완료되었습니다.");
            location.href = "/auth/user/login";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }

}
index.init();