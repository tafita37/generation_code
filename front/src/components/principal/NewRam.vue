<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertRam=reactive({
        idTypeRam : {},

    });
    var responseInsertRam=reactive({});

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

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/ram/insertRam";
            const response=axios.post(url, objectInsertRam);
            response.then(reponse => {
                Object.assign(responseInsertRam, reponse.data);
                console.log(responseInsertRam);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allRam");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle ram" :handle-submit="handleSubmit" submit-message="Inserer la  ram">
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
