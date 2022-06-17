$input vWorldPos, vNormal, vTexCoord0

#include <forward_pipeline.sh>

// Surface attributes
uniform vec4 t;

// Texture slots
SAMPLER2D(uColorMap, 0);
SAMPLER2D(uFadeMap, 1);

// Entry point of the forward pipeline default uber shader (Phong and PBR)
void main() {

	vec3 color = texture2D(uColorMap, vTexCoord0).xyz;
	float fade = texture2D(uFadeMap, vTexCoord0).x;

	//float opacity = mix(1.-fade,fade,t);
	// float opacity = t.x - fade;
	float opacity = fade > t.x?0.0:1.0;
	color = mix(vec3(1.0, 1.0, 1.0), color, opacity);

	gl_FragColor = vec4(color, 1.0);
}
