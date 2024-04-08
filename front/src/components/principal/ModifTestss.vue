<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertTestss = reactive({
        idtestss:route.query.idtestss,
        idtest : {},

    });

    const responseInsertTestss = reactive({});
    
            const allIdtest=reactive({});


    onMounted(() => {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
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

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/testss/insertTestss';
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.post(url, objectInsertTestss, { headers });
            Object.assign(responseInsertTestss, response);
            router.push("/allTestss");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la testss" :handle-submit="handleSubmit" submit-message="Modifier">
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
