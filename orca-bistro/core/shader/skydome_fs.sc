$input vWorldPos, vNormal, vTexCoord0, vTexCoord1, vTangent, vBinormal

#include <forward_pipeline.sh>

uniform vec4 uSkyDomeParam; // x: cloudyness, y: , z: , w: skydome emmisive value

// Texture slots
SAMPLER2D(uDiffuseMap, 3);
SAMPLER2D(uSelfMap, 4);

// Entry point of the forward pipeline default uber shader (Phong and PBR)
void main() {

	float shadow_width = uSkyDomeParam.y; // 0.005;
	vec3 cloud = texture2D(uDiffuseMap, vTexCoord0 + vec2(uClock.x * 0.00125, 0.0)).xyz;
	vec3 cloud_shadow = texture2D(uDiffuseMap, vTexCoord0 + vec2(uClock.x * 0.00125 + shadow_width, -shadow_width)).xyz;
	cloud_shadow.x = max(cloud_shadow.x - cloud.x * 0.75, 0.0);

	vec3 self = texture2D(uSelfMap, vTexCoord1).xyz;
	float l = (self.x + self.y + self.z) * 0.33333333;
	vec3 grey_sky = vec3(l, l, l);

	float kc = pow(vTexCoord1.y, 0.25);

	gl_FragColor = vec4((mix(self, grey_sky, pow(cloud_shadow.x, uSkyDomeParam.z)) + vec3(cloud.x, cloud.x, cloud.x) * uSkyDomeParam.x * kc) * uSkyDomeParam.w, 1.0);
}
