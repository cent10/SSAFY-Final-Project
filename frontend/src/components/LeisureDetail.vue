<template>
  <div class="leisure-detail">
    <b-container>
      <b-row align-h="center">
        <b-col>
          <div class="leisure-logo"></div>
        </b-col>
        <b-col>
          <ul>
            <li>카테고리</li>
            <li>업체명</li>
            <li>주소</li>
          </ul>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <div class="leisure-desc"></div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h5>상품리스트</h5>
          <b-list-group>
            <b-list-group-item>
              <b-container>
                <b-row>
                  <b-col>
                    <b-form-checkbox/>
                  </b-col>
                  <b-col>
                    상품명
                  </b-col>
                  <b-col>
                    설명
                  </b-col>
                  <b-col>
                    수량
                    <b-form-input type="number" class="col-md-3"/>
                    <b-btn variant="info btn-sm ml-1 mt-1 mb-1">+</b-btn>
                    <b-btn variant="warning btn-sm mr-1 mt-1 mb-1">-</b-btn>
                  </b-col>
                  <b-col>
                    일일판매수량
                  </b-col>
                </b-row>
              </b-container>
            </b-list-group-item>
          </b-list-group>
          <b-button>결제하기</b-button>
          <b-button>목록으로</b-button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
// import axios from 'axios';

export default {
  name: "LeisureDetail",
  data: function () {
    return {
      
    };
  },
};
</script>

<style scoped>
.leisure-detail {
  padding-top: 100px;
}
.leisure-logo {
  width: 100px;
  height: 100px;
  background-color: black;
}
.leisure-desc {
  height: 800px;
  background-color: gray;
}
</style>
