<template>
  <div class="footer">
  <mdb-footer color="stylish-color-dark" class="font-small pt-4 mt-4">
    <mdb-container class="text-center text-md-left">
      <mdb-row class="text-center text-md-left mt-3 pb-3">
        <mdb-col md="3" lg="3" xl="3" class="mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">Company name</h6>
          <p>SSAFY</p>
          <p>엑티비티 X</p>
        </mdb-col>
        <hr class="w-100 clearfix d-md-none" />
        <mdb-col md="2" lg="2" xl="2" class="mx-auto mt-4">
          <h6 class="text-uppercase mb-4 font-weight-bold">DEVELOPER</h6>
          <p>팀장</p>
          <p>테크리더</p>
          <p>FrontEnd</p>
          <p>BackEnd</p>
        </mdb-col>
        <hr class="w-100 clearfix d-md-none" />
        <mdb-col md="3" lg="2" xl="2" class="mx-auto mt-5">
          <h6 class="text-uppercase mb-3 font-weight-bold"></h6>
          <p>노지웅</p>
          <p>김태훈</p>
          <p>권혁규</p>
          <p>박진 박준수</p>
        </mdb-col>
        <p>
        </p>
        <hr class="w-100 clearfix d-md-none" />
        <mdb-col md="4" lg="3" xl="3" class="mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">Contact</h6>
          <p><i class="fas fa-home mr-3"></i> Multicampus, SSAFY</p>
          <p><i class="fas fa-envelope mr-3"></i> shwldnd06@naver.com</p>
          <p><i class="fas fa-phone mr-3"></i> 010 - 2613 - 7214</p>
          <!-- <p><i class="fas fa-print mr-3"></i> + 01 234 5</p> -->
        </mdb-col>
      </mdb-row>
      <hr />
      <mdb-row class="d-flex align-items-center">
        <mdb-col md="8" lg="8">
          <p class="text-center text-md-left grey-text">&copy; 2020 Copyright: <a href="http://k3a210.p.ssafy.io">
              http://k3a210.p.ssafy.io </a></p>
        </mdb-col>
        <!-- <mdb-col md="4" lg="4" class="ml-lg-0">
          <div class="text-center text-md-right">
            <ul class="list-unstyled list-inline">
              <li class="list-inline-item"><a class="btn-floating btn-sm rgba-white-slight mx-1"><i class="fab fa-facebook"></i></a></li>
              <li class="list-inline-item"><a class="btn-floating btn-sm rgba-white-slight mx-1"><i class="fab fa-twitter"></i></a></li>
              <li class="list-inline-item"><a class="btn-floating btn-sm rgba-white-slight mx-1"><i class="fab fa-google-plus"></i></a></li>
              <li class="list-inline-item"><a class="btn-floating btn-sm rgba-white-slight mx-1"><i class="fab fa-linkedin-in"></i></a></li>
            </ul>
          </div>
        </mdb-col> -->
      </mdb-row>
    </mdb-container>
  </mdb-footer>
  <!-- Footer -->
  </div>
</template>

<script>
  import { mdbFooter, mdbContainer, mdbRow, mdbCol } from 'mdbvue';
  export default {
    name: 'Footer',
    components: {
      mdbFooter,
      mdbContainer,
      mdbRow,
      mdbCol
    }
  }
</script>

<style scoped>
  .footer {
    background-color: #e8e8e8;
    margin-top: 0 auto;
  }
  
</style>