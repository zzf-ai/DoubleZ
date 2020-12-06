<template>
    <el-card class="register-form-layout">
        <h2 class="ms-title color-main">DoubleZ社区-欢迎注册</h2>
        <div class="ms-login">
            <el-form ref="form" :model="form" :rules="rules">
                <div v-if="errorInfo" style="margin-bottom: 5px;">
                    <span>{{errInfo}}</span>
                </div>
                <el-form-item prop="username">
                    <el-input v-model="form.username" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" type="password" placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.confirmPassword" type="password" placeholder="确认密码"></el-input>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input v-model="form.phone" placeholder="手机号"></el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input v-model="form.email" placeholder="电子邮箱"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit('form')">立即注册</el-button>
                    <el-button @click="onCancle()">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    export default {
        name: "register",
        data() {
            var confirmPassword = (rule,value,callback)=>{
                if(value===''){
                    callback(new Error('请再次输入密码'))
                }else if(value!==this.form.password){
                    callback(new Error('两次输入密码不一致'))
                }else{
                    callback()
                }
            };
            var validateEmail = (rule, value, callback) => {
                const reg = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
                if (value == '' || value == undefined || value == null) {
                    callback('请输入电子邮箱邮箱');
                } else {
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的邮箱地址'));
                    } else {
                        callback();
                    }
                }
            };
            var validatePhone = (rule, value, callback) => {
                const reg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (value == '' || value == undefined || value == null) {
                    callback('请输入手机号');
                } else {
                    if ((!reg.test(value)) && value != '') {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }
            };
            return {
                errorInfo: false,
                form: {
                    username: '',
                    password: '',
                    confirmPassword: '',
                    email: '',
                    phone: '',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, message: '密码最少6个字符', trigger: 'blur'},
                        {max: 20, message: '密码最多20个字符', trigger: 'blur'}
                    ],
                    confirmPassword: [
                        {required: true, validator: confirmPassword, trigger: 'blur'},
                        {min: 6, message: '密码最少6个字符', trigger: 'blur'},
                        {max: 20, message: '密码最多20个字符', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, validator: validateEmail, trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, validator: validatePhone, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            onSubmit(formName) {
                const self = this;
                self.$refs[formName].validate((valid) => {
                    if (valid) {
                        self.$axios.post('/register', self.form).then(function (response) {
                            console.log(response);
                            if (response.data.code == 400) {
                                self.errorInfo = true;
                                self.errInfo = response.data.msg;
                                self.$message.error(response.data.msg);
                            } else if (response.data.code == 200) {
                                self.$message.success(response.data.msg);
                                self.$router.push('/login');
                            }
                        }).then(function (error) {
                            console.log(error);
                        }).catch(error => {
                            self.errorInfo = true;
                            self.errInfo = response.data.msg;
                            self.$message.error(response.data.msg);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },
            onCancle() {
                this.$router.push('/login');
            },
            getDateTimes(str) {
                var str = new Date(str);
                return str;
            }
        }
    }
</script>

<style scoped>
    .register-form-layout {
        position: absolute;
        left: 0;
        right: 0;
        width: 360px;
        margin: 100px auto;
        /*border-top: 10px solid #53565a;*/
    }

    ms-title {
        text-align: center;

    }
    /*.ms-title {
        position: absolute;
        top: 50%;
        width: 100%;
        margin-top: -320px;
        text-align: center;
        font-size: 30px;
        color: #fff;

    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 300px;
        height: 490px;
        margin: -250px 0 0 -190px;
        padding: 40px;
        border-radius: 5px;
        background: #fff;
    }*/

    .ms-login span {
        color: red;
    }
</style>