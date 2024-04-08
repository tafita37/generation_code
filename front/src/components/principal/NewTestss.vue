<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertTestss=reactive({
        idtest : {},

    });
    var responseInsertTestss=reactive({});

            const allIdtest=reactive({});


    onMounted(() => {
        if(!localStorage.getItem("tokenLogin")) { router.push("/"); }
                const getAllIdtest = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/test/allTest/0';
                const token=localStorage.getItem("tokenLogin");
                const headers = {
                    Authorization: `Bearer ${token}`,
                };
                const response = await axios.get(urlApiSpringBoot, { headers });
                Object.assign(allIdtest, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdtest();
 
    });

    const handleSubmit = () => {
        try {
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const url="http://localhost:8080/testss/insertTestss";
            const response=axios.post(url, objectInsertTestss, { headers });
            response.then(reponse => {
                Object.assign(responseInsertTestss, reponse.data);
                console.log(responseInsertTestss);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allTestss/1");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle testss" :handle-submit="handleSubmit" submit-message="Inserer la  testss">
        <input v-model="objectInsertTestss.nomtestss" type="text">
<select  v-model="objectInsertTestss.idtest.idtest" id="">
    <option v-for="(idtest) in allIdtest" :key="idtest.idtest" :value="idtest.idtest">
        {{ idtest.label }}
    </option>
</select>

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
