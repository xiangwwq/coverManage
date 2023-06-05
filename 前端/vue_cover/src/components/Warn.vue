<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>日志管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card" v-loading="loading">
      <el-button type="primary" @click="refresh">刷新数据</el-button>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%"

         >
        <el-table-column label="日期">
          <template v-slot="scope">
            {{scope.row.sendTime.substring(0,10)}}
          </template>
        </el-table-column>

        <el-table-column label="时间">
          <template v-slot="scope">
            {{scope.row.sendTime.substring(11,19)}}
          </template>
        </el-table-column>
        <el-table-column
            prop="cid"
            label="井盖编号"
        >
        </el-table-column>
        <el-table-column
            prop="msg"
            label="警报信息"
        >
        </el-table-column>
        <el-table-column
            prop="man"
            label="负责人"
        >
        </el-table-column>
        <el-table-column label="处理状态">
          <template v-slot="scope">
            <el-tag  type="danger">待处理</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template v-slot="scope">
            <el-switch v-model="scope.row.state" @change="changeState(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageinfo.currentPage"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageTotal">
      </el-pagination>

    </el-card>
  </div>
</template>

<script>
export default {
  name: "Warn",
  data(){
    return{
      tableData:[],
      pageinfo:{
        currentPage:"",
        pageSize:""
      },
      pageTotal:"",
      state:"",
      loading:true
    }
  },
  methods:{
    QueryList(){
      var vm=this;
      let param = new URLSearchParams()
      param.append('currentPage',this.pageinfo.currentPage)
      param.append('pageSize',this.pageinfo.pageSize)

      this.$http.post("/log/listWarn",param).then(function (res){
        vm.tableData=res.data.data.page.list;
        vm.pageTotal=res.data.data.page.total;
        setTimeout(function () {
          // 这里就是处理的事件
          vm.loading=false
        }, 700);
      })
    },
    handleSizeChange(newPagesize){
      this.pageinfo.pageSize=newPagesize
      this.QueryList();
    },
    handleCurrentChange(newPage){
      this.pageinfo.currentPage=newPage
      this.QueryList();
    },
    //保存状态
    changeState(row){
      var vm=this;
      let param = new URLSearchParams()
      param.append('lid',row.lid)
      param.append('state',row.state)
       this.$http.post("/log/change",param).then(function (res){
            vm.$message.success(res.data.message)
         vm.QueryList()
       })
    },
    refresh(){
      var vm=this;
      this.$http.get("/log/refresh").then(function (res){
        vm.$message.success(res.data.message)
        vm.QueryList()
      })
    }
  },
  created() {
    this.QueryList()
  }
}
</script>

<style scoped>
.box-card {
  margin-top: 20px;
  min-height: 800px;

}
</style>
