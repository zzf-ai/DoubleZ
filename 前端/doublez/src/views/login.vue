<template>
   <!-- <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
    </el-container>-->
    <el-card class="login-form-layout">
        <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
            <h2 class="login-title color-main">DobleZ社区</h2>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-checkbox label="记住密码" name="type"></el-checkbox>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
            <el-form-item>
                <el-link type="primary" @click="doRegister()">用户注册</el-link>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "login",
        data() {
            return {
                ruleForm: {
                    username:'',
                    password:''
                },
                rules: {
                    username:[
                        {required:true,message:'请输入用户名',trigger:'blur'}
                    ],
                    password:[
                        {required:true,message:'请输入密码',trigger:'blur'},
                        {min: 6, message: '密码最少6个字符', trigger: 'blur'},
                        {max: 20, message: '密码最多20个字符', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this=this;//全局this，调用store
                        this.$axios.post('/login',this.ruleForm).then(res =>{
                            console.log(res)
                            const jwt=res.headers['authorization']
                            const userInfo=res.data.data
                            console.log(res.headers)
                            _this.$store.commit("SET_TOKEN",jwt)
                            _this.$store.commit("SET_USERINFO",userInfo)
                            //打印
                            console.log(_this.$store.getters.getUser)

                            _this.$message.success('登录成功');
                            //跳转
                            _this.$router.push("/success")
                        })/*.catch(error => {
                            _this.$message.error('账号或密码错误');
                        });*/
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            doRegister() {
                this.$router.push('/register');
            }
        }
    }
</script>

<style scoped>
    .login-form-layout {
        position: absolute;
        left: 0;
        right: 0;
        width: 360px;
        margin: 140px auto;
        /*border-top: 10px solid #53565a;*/
    }
    .login-title {
        text-align: center;
    }

   /* .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        !*background-color: #E9EEF3;*!
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }*/
</style>