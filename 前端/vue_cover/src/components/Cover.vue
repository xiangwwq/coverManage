<template>

  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>井盖管理</el-breadcrumb-item>
    </el-breadcrumb>



    <el-card class="box-card" v-loading="loading">

        <el-button type="primary"  @click="dialogVisible=true">添加井盖</el-button>

      <div class="bigbox">

        <div class="box_f" @click="getOne(item.cid)" v-for="(item,index) in coverList">
          <div class="box"></div>
          <div class="box_text">{{index+1}}号井</div>
        </div>

      </div>

    </el-card>

    <el-dialog
        title="添加井盖"
        :visible.sync="dialogVisible"
        width="50%"
    >
      <el-form   label-width="80px"  >
        <el-form-item label="井盖编号">
          <el-input v-model="coverform.cid"></el-input>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="coverform.man"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="coverform.address"></el-input>
        </el-form-item>
        <el-form-item label="经度">
          <el-input v-model="coverform.gpsEw"></el-input>
        </el-form-item>
        <el-form-item label="纬度">
          <el-input v-model="coverform.gpsNs"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="addCover()">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Cover",
  data(){
    return{
      coverList:[],
      loading:true,
      coverform:{
         cid:"",
         gpsNs:"",
         gpsEw:"",
         man:"",
         address:""
      },
      dialogVisible:false
    }
  },
  methods:{
    addCover(){
      this.dialogVisible = false
      var vm=this
      this.$http.post("/cover/add",this.coverform).then(function (res){
        vm.$message.success("添加成功")
        vm.getAll();
      },function (res){
        vm.$message.error("添加失败")
      })
    },
    getOne(cid){

      this.$router.push({
        path:'/coverone/'+cid,
      })

    },
    getAll(){
      var vm=this
      setTimeout(function () {
        // 这里就是处理的事件
        vm.loading=false
       }, 700);
      this.$http.get("/cover/list").then(function (res){
        vm.coverList=res.data.data.list

      })
    }
  },
  created() {
    this.getAll();
  }
}
</script>

<style scoped>
 .box-card{
   margin-top: 20px;
   min-height: 800px;
 }
 .bigbox{
   display: flex;
   /*flex-direction: row;*/
   flex-flow:row wrap
 }

 .box{
   margin-left: 100px;
   width:200px;
   height:200px;
   border:solid 1px black;
   background: indianred;
   border-radius: 100px;
   transform: rotateX(70deg);
   position: relative;
 }
 .box:after{
   content: '';
   display:inline-block;
   width:200px;
   height: 280px;
   position:relative;
   opacity: 0.6;
   background:indianred;
   top:50%;
   left:-0.5px;
   border-left:black 0.5px solid;
   border-right:black 0.5px solid;
   border-bottom: black 0.5px solid;
   border-bottom-left-radius: 100px;
   border-bottom-right-radius:100px;
 }
 .box_f{
   /*background-color: yellow;*/
   position: relative;
   height: 250px;
   display: flex;
   flex-direction: column;
   justify-content: center;

 }
 .box_f:hover{
   cursor: pointer;
   color: #409eff;
 }
 .box_text{
   /*background-color: #409eff;*/
     position: absolute;
     right: 23%;
     top:90%;
     font-weight: bold;
     font-size: 20px;
 }

</style>
