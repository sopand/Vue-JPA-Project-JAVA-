// vite.config.js
import { fileURLToPath, URL } from "node:url";
import { defineConfig } from "file:///C:/Users/pows1/OneDrive/%EB%B0%94%ED%83%95%20%ED%99%94%EB%A9%B4/Vue.js/project/project-team/node_modules/vite/dist/node/index.js";
import vue from "file:///C:/Users/pows1/OneDrive/%EB%B0%94%ED%83%95%20%ED%99%94%EB%A9%B4/Vue.js/project/project-team/node_modules/@vitejs/plugin-vue/dist/index.mjs";
var __vite_injected_original_import_meta_url = "file:///C:/Users/pows1/OneDrive/%EB%B0%94%ED%83%95%20%ED%99%94%EB%A9%B4/Vue.js/project/project-team/vite.config.js";
var vite_config_default = defineConfig({
  plugins: [vue()],
  server: {
    port: 3e3,
    proxy: {
      "/api": {
        // 요청 전달 대상 서버 주소 설정
        target: "http://localhost:8091/"
      }
    }
  },
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", __vite_injected_original_import_meta_url))
    }
  },
  css: {
    loaderOptions: {
      scss: {
        additionalData: `@import '@ckeditor/ckeditor5-vue/dist/ckeditor.css';`
      }
    }
  }
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcuanMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFxwb3dzMVxcXFxPbmVEcml2ZVxcXFxcdUJDMTRcdUQwRDUgXHVENjU0XHVCQTc0XFxcXFZ1ZS5qc1xcXFxwcm9qZWN0XFxcXHByb2plY3QtdGVhbVwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiQzpcXFxcVXNlcnNcXFxccG93czFcXFxcT25lRHJpdmVcXFxcXHVCQzE0XHVEMEQ1IFx1RDY1NFx1QkE3NFxcXFxWdWUuanNcXFxccHJvamVjdFxcXFxwcm9qZWN0LXRlYW1cXFxcdml0ZS5jb25maWcuanNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0M6L1VzZXJzL3Bvd3MxL09uZURyaXZlLyVFQiVCMCU5NCVFRCU4MyU5NSUyMCVFRCU5OSU5NCVFQiVBOSVCNC9WdWUuanMvcHJvamVjdC9wcm9qZWN0LXRlYW0vdml0ZS5jb25maWcuanNcIjtpbXBvcnQgeyBmaWxlVVJMVG9QYXRoLCBVUkwgfSBmcm9tICdub2RlOnVybCc7XG5cbmltcG9ydCB7IGRlZmluZUNvbmZpZyB9IGZyb20gJ3ZpdGUnO1xuaW1wb3J0IHZ1ZSBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUnO1xuXG4vLyBodHRwczovL3ZpdGVqcy5kZXYvY29uZmlnL1xuZXhwb3J0IGRlZmF1bHQgZGVmaW5lQ29uZmlnKHtcblx0cGx1Z2luczogW3Z1ZSgpXSxcblx0c2VydmVyOiB7XG5cdFx0cG9ydDogMzAwMCxcblx0XHRwcm94eToge1xuXHRcdFx0Jy9hcGknOiB7XG5cdFx0XHRcdC8vIFx1QzY5NFx1Q0NBRCBcdUM4MDRcdUIyRUMgXHVCMzAwXHVDMEMxIFx1QzExQ1x1QkM4NCBcdUM4RkNcdUMxOEMgXHVDMTI0XHVDODE1XG5cdFx0XHRcdHRhcmdldDogJ2h0dHA6Ly9sb2NhbGhvc3Q6ODA5MS8nLFxuXHRcdFx0fSxcblx0XHR9LFxuXHR9LFxuXHRyZXNvbHZlOiB7XG5cdFx0YWxpYXM6IHtcblx0XHRcdCdAJzogZmlsZVVSTFRvUGF0aChuZXcgVVJMKCcuL3NyYycsIGltcG9ydC5tZXRhLnVybCkpLFxuXHRcdH0sXG5cdH0sXG5cdGNzczoge1xuXHRcdGxvYWRlck9wdGlvbnM6IHtcblx0XHRcdHNjc3M6IHtcblx0XHRcdFx0YWRkaXRpb25hbERhdGE6IGBAaW1wb3J0ICdAY2tlZGl0b3IvY2tlZGl0b3I1LXZ1ZS9kaXN0L2NrZWRpdG9yLmNzcyc7YCxcblx0XHRcdH0sXG5cdFx0fSxcblx0fSxcbn0pO1xuIl0sCiAgIm1hcHBpbmdzIjogIjtBQUErWSxTQUFTLGVBQWUsV0FBVztBQUVsYixTQUFTLG9CQUFvQjtBQUM3QixPQUFPLFNBQVM7QUFIeU4sSUFBTSwyQ0FBMkM7QUFNMVIsSUFBTyxzQkFBUSxhQUFhO0FBQUEsRUFDM0IsU0FBUyxDQUFDLElBQUksQ0FBQztBQUFBLEVBQ2YsUUFBUTtBQUFBLElBQ1AsTUFBTTtBQUFBLElBQ04sT0FBTztBQUFBLE1BQ04sUUFBUTtBQUFBO0FBQUEsUUFFUCxRQUFRO0FBQUEsTUFDVDtBQUFBLElBQ0Q7QUFBQSxFQUNEO0FBQUEsRUFDQSxTQUFTO0FBQUEsSUFDUixPQUFPO0FBQUEsTUFDTixLQUFLLGNBQWMsSUFBSSxJQUFJLFNBQVMsd0NBQWUsQ0FBQztBQUFBLElBQ3JEO0FBQUEsRUFDRDtBQUFBLEVBQ0EsS0FBSztBQUFBLElBQ0osZUFBZTtBQUFBLE1BQ2QsTUFBTTtBQUFBLFFBQ0wsZ0JBQWdCO0FBQUEsTUFDakI7QUFBQSxJQUNEO0FBQUEsRUFDRDtBQUNELENBQUM7IiwKICAibmFtZXMiOiBbXQp9Cg==
