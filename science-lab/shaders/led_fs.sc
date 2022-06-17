$input vWorldPos, vNormal, vTexCoord0, vTexCoord1, vTangent, vBinormal, vLinearShadowCoord0, vLinearShadowCoord1, vLinearShadowCoord2, vLinearShadowCoord3, vSpotShadowCoord

#include <forward_pipeline.sh>

// Surface attributes
uniform vec4 uDiffuseColor;
uniform vec4 uColorOpacity;

// Texture slots
SAMPLER2D(uDiffuseMap, 3); // PBR metalness in alpha

// Entry point of the forward pipeline default uber shader (Phong and PBR)
void main() {

    vec3 diff = texture2D(uDiffuseMap, vTexCoord0).xyz;

	gl_FragColor = vec4(diff * uColorOpacity.xyz * uColorOpacity.w, 1.0);
}