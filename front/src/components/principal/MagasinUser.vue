<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allMagasinUser=reactive({});

    const getAllMagasinUser = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/magasinUser/allMagasinUser';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allMagasinUser, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idMagasinUser) => {
        router.push({path : "/modifMagasinUser", query: {idMagasinUser : idMagasinUser}});
    }

    const deleteMagasinUserById = (idMagasinUser) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/magasinUser/deleteMagasinUserById?idMagasinUser='+idMagasinUser;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllMagasinUser);
</script>
<template>
    <TitleListe title-liste="Liste des magasinUser"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(magasinUser)  in allMagasinUser" :key="magasinUser.idMagasinUser">
            <H5TitleListe labels="" :title-h5="magasinUser.idMagasinUser"/>
            <SpanListe :title-span='magasinUser.idMagasinUser' labels=''/><SpanListe :title-span='magasinUser.nomMagasinUser' labels=''/><SpanListe :title-span='magasinUser.mdp' labels=''/><SpanListe :title-span='magasinUser.role' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(magasinUser.idMagasinUser)" :funct-delete="() => deleteMagasinUserById(magasinUser.idMagasinUser)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
