import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import TipList from "@/components/TipList.vue";
import TipDetail from "@/components/TipDetail.vue";
import TipUpdate from "@/components/TipUpdate.vue";
import TipWrite from "@/components/TipWrite.vue";

import TotalResult from "@/components/TotalResult.vue";

import NoticeList from "@/components/NoticeList.vue";
import NoticeDetail from "@/components/NoticeDetail.vue";
import NoticeWrite from "@/components/NoticeWrite.vue";
import NoticeUpdate from "@/components/NoticeUpdate.vue";

import Login from "@/components/Login.vue";

import LeisureList from "@/components/LeisureList.vue";
import LeisureDetail from "@/components/LeisureDetail.vue";

import KakaoSuccess from "@/components/KakaoSuccess.vue";
import KakaoFail from "@/components/KakaoFail.vue";

import UpdateUser from "@/components/UpdateUser.vue";

import MyProfile from "@/components/MyProfile.vue";

import EquipList from "@/components/EquipList.vue";
import EquipDetail from "@/components/EquipDetail.vue";

import UpdateReview from "@/components/UpdateReview.vue";
import ReviewDetail from "@/components/ReviewDetail.vue";
import WriteReview from "@/components/WriteReview.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/updateuser",
    name: "UpdateUser",
    component: UpdateUser,
  },
  {
    path: "/tiplist",
    name: "TipList",
    component: TipList,
    props: true,
  },
  {
    path: "/tipdetail/:id",
    name: "TipDetail",
    component: TipDetail,
  },
  {
    path: "/tipupdate/:id",
    name: "TipUpdate",
    component: TipUpdate,
  },
  {
    path: "/tip/create/",
    name: "TipWrite",
    component: TipWrite,
  },
  {
    path: "/totalresult/:word",
    name: "TotalResult",
    component: TotalResult,
    props: true,
  },
  {
    path: "/noticelist",
    name: "NoticeList",
    component: NoticeList,
  },
  {
    path: "/notice/create/",
    name: "NoticeWrite",
    component: NoticeWrite,
  },
  {
    path: "/noticedetail/:id",
    name: "NoticeDetail",
    component: NoticeDetail,
  },
  {
    path: "/noticeupdate/:id",
    name: "NoticeUpdate",
    component: NoticeUpdate,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/LeisureDetail/:id",
    name: "LeisureDetail",
    component: LeisureDetail,
  },
  {
    path: "/leisurelist",
    name: "LeisureList",
    component: LeisureList,
    props: true,
  },
  {
    path: "/kakaopay/success",
    name: "KakaoSuccess",
    component: KakaoSuccess,
    props: (route) => ({ query: route.query.pg_token }),
  },
  {
    path: "/kakaopay/fail",
    name: "KakaoFail",
    component: KakaoFail,
  },
  {
    path: "/myprofile",
    name: "MyProfile",
    component: MyProfile,
  },
  {
    path: "/equipDetail/:id",
    name: "EquipDetail",
    component: EquipDetail,
  },
  {
    path: "/equiplist",
    name: "EquipList",
    component: EquipList,
    props: true,
  },
  {
    path: "/writereview/:id",
    name: "WriteReview",
    component: WriteReview
  },
  {
    path: "/updatereview/:id",
    name: "UpdateReview",
    component: UpdateReview
  },
  {
    path: "/reviewdetail/:id",
    name: "ReviewDetail",
    component: ReviewDetail
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['Home']  // Login 안해도 됨
//   const authPages = []  // Login 되어있으면 안됨
//   const loginRequiredPages = []

//   const authRequired = !publicPages.includes(to.name)  // 로그인 해야 함.
//   const unauthRequired = authPages.includes(to.name)  // 로그인 해서는 안됨
//   const loginRequired = loginRequiredPages.includes(to.name)
//   const isLoggedIn = !!Vue.$cookies.isKey('auth-token')

//   if (unauthRequired && isLoggedIn) {
//     next("/");
//   }
//   if (loginRequired && !isLoggedIn) {
//     alert('로그인이 필요합니다.')
//     next({ name: "Login" })
//   }
//   authRequired && !isLoggedIn ? next({ name: "Login" }) : next();
// });
export default router;
