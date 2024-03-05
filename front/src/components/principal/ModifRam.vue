<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertRam = reactive({
        idRam:route.query.idRam,
        idTypeRam : {},

    });

    const responseInsertRam = reactive({});
    
            const allIdTypeRam=reactive({});


    onMounted(() => {
                const getAllIdTypeRam = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/typeRam/allTypeRam';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdTypeRam, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdTypeRam();
 
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/ram/insertRam';
            const response = await axios.post(url, objectInsertRam);
            Object.assign(responseInsertRam, response);
            router.push("/allRam");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la ram" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertRam.capacite" type="number">
<input v-model="objectInsertRam.frequence" type="number">
<select  v-model="objectInsertRam.idTypeRam.idTypeRam" id="">
    <option v-for="(idTypeRam) in allIdTypeRam" :key="idTypeRam.idTypeRam" :value="idTypeRam.idTypeRam">
        {{ idTypeRam.label }}
    </option>
</select>

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
