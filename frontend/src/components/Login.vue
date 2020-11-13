<template>
    <div class="login" v-if="!isMember">
        <b-form-group
            id="nickname"
            description="야!올레?에서 사용할 닉네임을 입력해주세요."
            label="닉네임"
            label-for="nickname-input"
            :invalid-feedback="invalidFeedback"
            :state="state"
        >
      <b-form-input id="nickname-input" v-model="nickname" :state="state" trim></b-form-input>
    </b-form-group>
        <b-button @click="join" type="button">가입하기</b-button>
    </div>
</template>
<script>

const API_URL = process.env.VUE_APP_SERVER_URL;

import axios from 'axios';

export default {
    name: 'Login',
    data() {
        return{
            nickname:"",
            token: "",
            ukey: 0,
            uid: 0,
            isMember: true,
        };
    },
    created() {
        axios({
            method: "GET",
            url: `${API_URL}/login`,
            params: {
                code: this.$route.query.code
            }
        })
        .then(({ data }) => {
            this.nickname = data.name;
            this.token = data.acess_token;
            this.ukey = data.ukey;
            this.uid = data.id;
            if(data.name !== null){
                this.$cookies.set("yol_token", this.token);
                this.$cookies.set("yol_nickname", this.nickname);
                this.$cookies.set("yol_ukey", this.ukey);
                this.$cookies.set("yol_uid", this.uid);
                this.$router.push({path: "/"});
            }
            this.isMember = false;
        })
        .catch((err) => {
            console.log(err);
            alert("로그인 과정 중 에러가 발생했습니다.");
            this.$router.push({path: "/"});
        });
    },
    computed: {
      state() {
        if(this.nickname === null)
          return false;
        return this.nickname.length >= 2;
      },
      invalidFeedback() {
        if (this.nickname === null || this.nickname.length < 2) {
            return '2글자 이상입력해주세요.';
        } return '';
     }
    },
    methods: {
        join() {
            if(this.nickname === null || this.nickname === ""){
                alert("닉네임을 입력해주세요!");
                return;
            }
            axios({
                method: "POST",
                url: `${API_URL}/user/create`,
                data: {
                    ukey: this.ukey,
                    name: this.nickname
                }
            })
            .then(() => {
                alert(this.nickname + "님 환영합니다!!");
                this.$cookies.set("yol_token", this.token);
                this.$cookies.set("yol_nickname", this.nickname);
                this.$cookies.set("yol_ukey", this.ukey);
                this.$cookies.set("yol_uid", this.uid);
                this.$router.push({path: "/"});
            })
            .catch((err) => {
                console.log(err);
                alert("가입을 처리하던 중 에러가 발생했습니다.");
                this.$router.push({path: "/"});
            });
        }
    }
}
</script>
<style scoped>
    .login {
        padding-top: 100px;
        margin: auto;
        width: 300px;
    }
</style>