#ifndef __X10_COMPILER_WS_BOXEDBOOLEAN_H
#define __X10_COMPILER_WS_BOXEDBOOLEAN_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { namespace ws { 

class BoxedBoolean : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10_boolean FMGL(value);
    
    virtual x10aux::ref<x10::compiler::ws::BoxedBoolean> x10__compiler__ws__BoxedBoolean____x10__compiler__ws__BoxedBoolean__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::compiler::ws::BoxedBoolean> _make();
    
    void __fieldInitializers1797();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_BOXEDBOOLEAN_H

namespace x10 { namespace compiler { namespace ws { 
class BoxedBoolean;
} } } 

#ifndef X10_COMPILER_WS_BOXEDBOOLEAN_H_NODEPS
#define X10_COMPILER_WS_BOXEDBOOLEAN_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Boolean.h>
#ifndef X10_COMPILER_WS_BOXEDBOOLEAN_H_GENERICS
#define X10_COMPILER_WS_BOXEDBOOLEAN_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::ws::BoxedBoolean::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::ws::BoxedBoolean> this_ = new (memset(x10aux::alloc<x10::compiler::ws::BoxedBoolean>(), 0, sizeof(x10::compiler::ws::BoxedBoolean))) x10::compiler::ws::BoxedBoolean();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_WS_BOXEDBOOLEAN_H_GENERICS
#endif // __X10_COMPILER_WS_BOXEDBOOLEAN_H_NODEPS
