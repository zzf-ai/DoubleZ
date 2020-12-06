<template>
    <div>
        <h1>Success</h1>
        <Button @click="logout()">注销</Button>
    </div>
</template>
<script>
    //import route_list from '../components/route_list'
    export default {
        /*components:{
            route_list
        },*/
        /*data(){
            return{

            }
        },*/
        methods:{
            logout(){
                const self = this;
                this.$axios.get('http://localhost:8081/logout').then(function (response) {
                    console.log(response);
                    if (response.data.code == 401) {
                        console.log("没权限")
                        /*self.errorInfo = true;
                        self.errInfo = response.data.msg;
                        self.$message.error(response.data.msg);*/
                    } else if (response.data.code == 200) {
                        this.$store.commit('REMOVE_INFO')
                        console.log(_this.$store.getters.getUser)
                        //跳转
                        _this.$router.push("/login")
                    }
                }).then(function (error) {
                    console.log(error);
                }).catch(error => {
                    /*self.errorInfo = true;
                    self.errInfo = response.data.msg;
                    self.$message.error(response.data.msg);*/
                });/*data=>data.data);*/
            }
        },

    }
</script>

<style scoped>

</style>